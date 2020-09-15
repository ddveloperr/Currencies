package com.example.currencies.ui.fragment.mvi

import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.action.MviAction
import java.math.BigDecimal

sealed class CurrenciesViewAction : MviAction {
    object StartFirstLoad : CurrenciesViewAction()
    data class OnItemClicked(val item: CurrencyViewHolderItem) : CurrenciesViewAction()
    data class OnEditValueChanged(val item: CurrencyViewHolderItem, val value: BigDecimal?) :
        CurrenciesViewAction()
}