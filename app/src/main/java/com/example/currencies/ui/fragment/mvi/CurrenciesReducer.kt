package com.example.currencies.ui.fragment.mvi

import com.example.common.model.StringSource
import com.example.currencies.domain.model.CurrenciesResponse
import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.model.mapToViewModel
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.MviReducer
import java.math.BigDecimal
import javax.inject.Inject

class CurrenciesReducer @Inject constructor() :
    MviReducer<CurrenciesViewState, CurrenciesPartialState> {


    override fun reduce(
        previousState: CurrenciesViewState,
        change: CurrenciesPartialState
    ): CurrenciesViewState {
        return when (change) {
            is CurrenciesPartialState.Loading -> reduceLoadingState(previousState)
            is CurrenciesPartialState.DataLoaded -> reduceDataLoadedState(
                change,
                previousState
            )
            is CurrenciesPartialState.FirstLoadError -> reduceFirstLoadError(
                change,
                previousState
            )
            is CurrenciesPartialState.OnItemClicked -> reduceOnItemClicked(change, previousState)
            is CurrenciesPartialState.Empty -> previousState
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
            data = getDataLoadedList(partialState.data, partialState.multiplicator),
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

    private fun reduceOnItemClicked(
        partialState: CurrenciesPartialState.OnItemClicked,
        previousState: CurrenciesViewState
    ): CurrenciesViewState {
        val items = previousState.data!!.items.toMutableList()
        val item = partialState.item
        items.remove(item)
        items.add(0, item)
        return CurrenciesViewState(
            CurrenciesViewState.Data(
                items,
                CurrenciesInitialState(item.currency, item.value)
            ), isLoading = false, error = null
        )
    }

    private fun getDataLoadedList(
        response: CurrenciesResponse,
        multiplicator: BigDecimal
    ): CurrenciesViewState.Data {
        val items = mutableListOf<CurrencyViewHolderItem>()
        items.add(
            getCurrencyViewHolderItem(
                Currency.valueOf(response.baseCurrency),
                multiplicator
            )
        )
        items.addAll(response.currencyRates.map {
            getCurrencyViewHolderItem(it.currency, it.rate * multiplicator)
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
            value = rate,
            currency = currency
        )
    }
}