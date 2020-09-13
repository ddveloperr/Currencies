package com.example.currencies.ui.fragment.mvi

import com.example.currencies.common.utils.toObservable
import com.example.currencies.domain.CurrenciesRepository
import io.reactivex.Observable
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(private val repository: CurrenciesRepository) {

    fun onAction(viewAction: CurrenciesViewAction): Observable<CurrenciesPartialState> {
        return when (viewAction) {
            CurrenciesViewAction.StartFirstLoad -> onStartFirstLoad()
        }
    }

    private fun onStartFirstLoad(): Observable<CurrenciesPartialState> {
        return repository.getCurrencyRates("USD").flatMapObservable {
            CurrenciesPartialState.DataLoaded(it).toObservable()
        }.cast(CurrenciesPartialState::class.java)
            .startWith(CurrenciesPartialState.Loading)
            .onErrorReturn { CurrenciesPartialState.FirstLoadError(it) }
    }
}