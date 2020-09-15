package com.example.currencies.ui.fragment.mvi

import com.example.common.ext.toObservable
import com.example.currencies.domain.CurrenciesRepository
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.MviSubscriptions
import com.example.mvi.domain.MviUseCase
import io.reactivex.Observable
import java.math.BigDecimal
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(private val repository: CurrenciesRepository) :
    MviUseCase<CurrenciesViewAction, CurrenciesInitialState, CurrenciesPartialState, MviSubscriptions>() {

    override fun onAction(
        action: CurrenciesViewAction,
        state: CurrenciesInitialState
    ): Observable<CurrenciesPartialState> {
        return when (action) {
            is CurrenciesViewAction.StartFirstLoad -> onStartFirstLoad(state)
            is CurrenciesViewAction.OnItemClicked -> onItemClicked(action.item)
            is CurrenciesViewAction.OnEditValueChanged -> onEditValueChanged(action.item, action.value)
        }
    }

    private fun onStartFirstLoad(state: CurrenciesInitialState): Observable<CurrenciesPartialState> {
        return repository.getCurrencyRates(state.baseCurrency.name).flatMapObservable {
            CurrenciesPartialState.DataLoaded(it, state.multiplicator).toObservable()
        }.cast(CurrenciesPartialState::class.java)
            .startWith(CurrenciesPartialState.Loading)
            .onErrorReturn { CurrenciesPartialState.FirstLoadError(it) }
    }

    private fun onItemClicked(
        item: CurrencyViewHolderItem
    ): Observable<CurrenciesPartialState> {
        return CurrenciesPartialState.OnItemClicked(item).toObservable()
    }

    private fun onEditValueChanged(item: CurrencyViewHolderItem, value: BigDecimal?): Observable<CurrenciesPartialState> {
        return CurrenciesPartialState.OnEditValueChanged(item, value).toObservable()
    }
}