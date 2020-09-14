package com.example.currencies.ui.fragment.mvi

import com.example.common.ext.toObservable
import com.example.currencies.domain.CurrenciesRepository
import com.example.mvi.MviSubscriptions
import com.example.mvi.domain.MviUseCase
import io.reactivex.Observable
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(private val repository: CurrenciesRepository) :
    MviUseCase<CurrenciesViewAction, CurrenciesInitialState, CurrenciesPartialState, MviSubscriptions>() {

    override fun onAction(
        action: CurrenciesViewAction,
        state: CurrenciesInitialState
    ): Observable<CurrenciesPartialState> {
        return when (action) {
            CurrenciesViewAction.StartFirstLoad -> onStartFirstLoad(state)
            is CurrenciesViewAction.OnItemClicked -> onItemClicked(state)
        }
    }

    private fun onStartFirstLoad(state: CurrenciesInitialState): Observable<CurrenciesPartialState> {
        return repository.getCurrencyRates(state.baseCurrency.name).flatMapObservable {
            CurrenciesPartialState.DataLoaded(it, state.multiplicator).toObservable()
        }.cast(CurrenciesPartialState::class.java)
            .startWith(CurrenciesPartialState.Loading)
            .onErrorReturn { CurrenciesPartialState.FirstLoadError(it) }
    }

    private fun onItemClicked(state: CurrenciesInitialState): Observable<CurrenciesPartialState> {
        return Observable.just(CurrenciesPartialState.Empty)
    }
}