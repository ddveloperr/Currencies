package com.example.currencies.ui.fragment.mvi

import com.example.currencies.domain.model.Currency
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.state.MviViewState
import java.math.BigDecimal

data class CurrenciesViewState(
    val data: Data? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
) : MviViewState {
    data class Data(
        val baseCurrencyItem: CurrencyViewHolderItem,
        val items: List<CurrencyViewHolderItem>,
        val currencyRates: Map<Currency, BigDecimal>? = null,
        val initialState: CurrenciesInitialState? = null
    ) {
        fun getList(): List<CurrencyViewHolderItem> {
            return mutableListOf<CurrencyViewHolderItem>().apply {
                add(baseCurrencyItem)
                addAll(items)
            }
        }
    }
}