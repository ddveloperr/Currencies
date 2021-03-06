package com.example.currencies.ui.fragment.mvi

import com.example.common.ext.toObservable
import com.example.currencies.domain.CurrenciesRepository
import com.example.currencies.domain.model.CurrenciesResponse
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.domain.MviUseCase
import io.reactivex.Observable
import java.math.BigDecimal
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(private val repository: CurrenciesRepository) :
    MviUseCase<CurrenciesViewAction, CurrenciesInitialState, CurrenciesPartialState, CurrenciesSubscriptions>() {

    override fun onAction(
        action: CurrenciesViewAction,
        state: CurrenciesInitialState
    ): Observable<CurrenciesPartialState> {
        return when (action) {
            is CurrenciesViewAction.StartFirstLoad -> onStartFirstLoad(state)
            is CurrenciesViewAction.OnItemClicked -> onItemClicked(action)
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
        action: CurrenciesViewAction.OnItemClicked
    ): Observable<CurrenciesPartialState> {
        subscriptionsSubject.onNext(CurrenciesSubscriptions.StopRateUpdate)
        return repository.getCurrencyRates(action.item.currency.name).flatMapObservable {
            subscriptionsSubject.onNext(CurrenciesSubscriptions.StartRateUpdate(action.item.currency))
            CurrenciesPartialState.DataLoaded(it, action.item.getDisplayValue()).toObservable()
        }.cast(CurrenciesPartialState::class.java)
            .startWith(CurrenciesPartialState.OnItemClicked(action.item))
            .doOnComplete { subscriptionsSubject.onNext(CurrenciesSubscriptions.ScrollToTop) }
            .onErrorReturnItem(CurrenciesPartialState.Empty)
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