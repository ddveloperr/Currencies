package com.example.currencies.ui.fragment.mvi

import com.example.common.model.StringSource
import com.example.currencies.domain.model.CurrenciesResponse
import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.model.mapToViewModel
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import java.math.BigDecimal
import javax.inject.Inject

class CurrenciesReducer @Inject constructor() {

    fun reduce(
        previousState: CurrenciesViewState,
        partialState: CurrenciesPartialState
    ): CurrenciesViewState {
        return when (partialState) {
            is CurrenciesPartialState.Loading -> reduceLoadingState(previousState)
            is CurrenciesPartialState.DataLoaded -> reduceDataLoadedState(
                partialState,
                previousState
            )
            is CurrenciesPartialState.FirstLoadError -> reduceFirstLoadError(
                partialState,
                previousState
            )
        }
    }

    private fun reduceLoadingState(previousState: CurrenciesViewState): CurrenciesViewState {
        return previousState.copy(data = null, isLoading = true, error = null)
    }

    private fun reduceDataLoadedState(
        partialState: CurrenciesPartialState.DataLoaded,
        previousState: CurrenciesViewState
    ): CurrenciesViewState {
        return previousState.copy(
            data = getDataLoadedList(partialState.data),
            isLoading = false,
            error = null
        )
    }

    private fun reduceFirstLoadError(
        partialState: CurrenciesPartialState.FirstLoadError,
        previousState: CurrenciesViewState
    ): CurrenciesViewState {
        return previousState.copy(data = null, isLoading = false, error = partialState.throwable)
    }

    private fun getDataLoadedList(response: CurrenciesResponse): CurrenciesViewState.Data {
        val items = mutableListOf<CurrencyViewHolderItem>()
        items.add(
            getCurrencyViewHolderItem(
                Currency.valueOf(response.baseCurrency),
                BigDecimal.TEN
            )
        )
        items.addAll(response.currencyRates.map {
            getCurrencyViewHolderItem(it.currency, BigDecimal.valueOf(it.rate))
        })
        return CurrenciesViewState.Data(items)
    }

    private fun getCurrencyViewHolderItem(
        currency: Currency,
        rate: BigDecimal
    ): CurrencyViewHolderItem {
        val currencyViewModel = currency.mapToViewModel()
        return CurrencyViewHolderItem(
            title = StringSource.Text(currency.name),
            subtitle = currencyViewModel.nameRes?.let { StringSource.Resource(it) },
            icon = currencyViewModel.iconRes,
            value = rate
        )
    }
}