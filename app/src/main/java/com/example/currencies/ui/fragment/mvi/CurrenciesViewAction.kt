package com.example.currencies.ui.fragment.mvi

sealed class CurrenciesViewAction {
    object StartFirstLoad: CurrenciesViewAction()
}