package com.example.currencies.ui.fragment.mvi

import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.state.MviViewState

data class CurrenciesViewState(
    val data: Data? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
): MviViewState {
    class Data(val items: List<CurrencyViewHolderItem>, val initialState: CurrenciesInitialState? = null)
}