package com.example.currencies.ui.fragment.mvi

import com.example.mvi.action.MviAction

sealed class CurrenciesViewAction: MviAction {
    object StartFirstLoad: CurrenciesViewAction()
}