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

    companion object {
        private val BASE_CURRENCY_RATE = BigDecimal.ONE
    }


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
            is CurrenciesPartialState.OnEditValueChanged -> reduceOnEditValueChanged(
                change,
                previousState
            )
            is CurrenciesPartialState.OnCurrencyRateUpdate -> reduceOnCurrencyRateUpdate(
                change,
                previousState
            )
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
        items.removeAt(items.indexOfFirst { it.currency === item.currency })
        items.add(0, previousState.data.baseCurrencyItem)
        return previousState.copy(
            data = previousState.data.copy(
                baseCurrencyItem = item,
                items = items
            )
        )
    }

    private fun reduceOnEditValueChanged(
        partialState: CurrenciesPartialState.OnEditValueChanged,
        previousState: CurrenciesViewState
    ): CurrenciesViewState {
        return previousState.copy(
            data = previousState.data!!.copy(
                baseCurrencyItem = previousState.data.baseCurrencyItem.copy(multiplicator = partialState.value),
                items = previousState.data.items.map { it.copy(multiplicator = partialState.value) }
            )
        )
    }

    private fun reduceOnCurrencyRateUpdate(
        partialState: CurrenciesPartialState.OnCurrencyRateUpdate,
        previousState: CurrenciesViewState
    ): CurrenciesViewState {
        return previousState.copy(
            data = updateListRate(partialState.currenciesResponse, previousState),
            isLoading = false,
            error = null
        )
    }

    private fun updateListRate(
        currenciesResponse: CurrenciesResponse,
        previousState: CurrenciesViewState
    ): CurrenciesViewState.Data {
        val newItems = previousState.data!!.items.map {
            it.copy(rate = currenciesResponse.currencyRates.getValue(it.currency))
        }
        return previousState.data.copy(items = newItems)
    }

    private fun getDataLoadedList(
        response: CurrenciesResponse,
        multiplicator: BigDecimal?
    ): CurrenciesViewState.Data {
        val items = mutableListOf<CurrencyViewHolderItem>()
        val baseCurrency = response.baseCurrency
        Currency.values().forEach {
            if (it.name != baseCurrency) {
                items.add(
                    getCurrencyViewHolderItem(
                        it,
                        response.currencyRates.getValue(it),
                        multiplicator
                    )
                )
            }
        }
        return CurrenciesViewState.Data(
            getCurrencyViewHolderItem(
                Currency.valueOf(baseCurrency),
                BASE_CURRENCY_RATE,
                multiplicator,
                isBaseCurrency = true
            ), items
        )
    }

    private fun getCurrencyViewHolderItem(
        currency: Currency,
        rate: BigDecimal,
        multiplicator: BigDecimal?,
        isBaseCurrency: Boolean = false
    ): CurrencyViewHolderItem {
        val currencyViewModel = currency.mapToViewModel()
        return CurrencyViewHolderItem(
            title = StringSource.Text(currency.name),
            subtitle = currencyViewModel.nameRes?.let { StringSource.Resource(it) },
            icon = currencyViewModel.iconRes,
            rate = rate,
            multiplicator = multiplicator,
            currency = currency,
            isBaseCurrency = isBaseCurrency
        )
    }
}