package com.example.currencies.ui.fragment.mvi

import com.example.currencies.ui.adapter.CurrencyViewHolderItem

class CurrenciesViewState(
    val data: Data? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
) {
    class Data(val items: List<CurrencyViewHolderItem>)
}