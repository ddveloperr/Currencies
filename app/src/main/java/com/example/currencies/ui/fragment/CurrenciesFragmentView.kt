package com.example.currencies.ui.fragment

import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.MviView

interface CurrenciesFragmentView: MviView {
    fun render(items: List<CurrencyViewHolderItem>)
    fun showProgressBar()
    fun hideProgressBar()
}