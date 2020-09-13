package com.example.currencies.ui.fragment

import com.example.currencies.ui.fragment.mvi.CurrenciesViewState
import com.hannesdorfmann.mosby3.mvp.MvpView

interface CurrenciesFragmentView: MvpView {
    fun render(state: CurrenciesViewState)
}