package com.example.currencies.ui.fragment.mvi

import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.action.MviAction

sealed class CurrenciesViewAction: MviAction {
    object StartFirstLoad: CurrenciesViewAction()
    data class OnItemClicked(val item: CurrencyViewHolderItem): CurrenciesViewAction()
}