package com.example.currencies.ui.fragment.mvi

import com.example.common.ext.toObservable
import com.example.currencies.domain.CurrenciesRepository
import com.example.currencies.domain.model.CurrenciesResponse
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.domain.MviUseCase
import io.reactivex.Observable
import java.math.BigDecimal
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(private val repository: CurrenciesRepository) :
    MviUseCase<CurrenciesViewAction, CurrenciesInitialState, CurrenciesPartialState, CurrenciesSubscriptions>() {

    override fun onAction(
        action: CurrenciesViewAction,
        state: CurrenciesInitialState
    ): Observable<CurrenciesPartialState> {
        return when (action) {
            is CurrenciesViewAction.StartFirstLoad -> onStartFirstLoad(state)
            is CurrenciesViewAction.OnItemClicked -> onItemClicked(action.item)
            is CurrenciesViewAction.OnEditValueChanged -> onEditValueChanged(
                action.item,
                action.value
            )
            is CurrenciesViewAction.OnCurrencyRateUpdate -> onCurrencyRateUpdate(action.currenciesResponse)
        }
    }

    private fun onStartFirstLoad(state: CurrenciesInitialState): Observable<CurrenciesPartialState> {
        return repository.getCurrencyRates(state.baseCurrency.name).flatMapObservable {
            subscriptionsSubject.onNext(CurrenciesSubscriptions.StartRateUpdate(state.baseCurrency))
            CurrenciesPartialState.DataLoaded(it, state.multiplicator).toObservable()
        }.cast(CurrenciesPartialState::class.java)
            .startWith(CurrenciesPartialState.Loading)
            .onErrorReturn { CurrenciesPartialState.FirstLoadError(it) }

    }

    private fun onItemClicked(
        item: CurrencyViewHolderItem
    ): Observable<CurrenciesPartialState> {
        return CurrenciesPartialState.OnItemClicked(item).toObservable().cast(CurrenciesPartialState::class.java).doOnNext {
            subscriptionsSubject.onNext(CurrenciesSubscriptions.StartRateUpdate(item.currency))
        }
    }

    private fun onEditValueChanged(
        item: CurrencyViewHolderItem,
        value: BigDecimal?
    ): Observable<CurrenciesPartialState> {
        return CurrenciesPartialState.OnEditValueChanged(item, value).toObservable()
    }

    private fun onCurrencyRateUpdate(currenciesResponse: CurrenciesResponse): Observable<CurrenciesPartialState> {
        return CurrenciesPartialState.OnCurrencyRateUpdate(currenciesResponse).toObservable()
    }
}