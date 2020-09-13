package com.example.currencies.ui.fragment

import com.example.currencies.ui.adapter.CurrencyViewHolderItem
import com.hannesdorfmann.mosby3.mvp.MvpView

interface CurrenciesFragmentView: MvpView {
    fun render(items: List<CurrencyViewHolderItem>)
}