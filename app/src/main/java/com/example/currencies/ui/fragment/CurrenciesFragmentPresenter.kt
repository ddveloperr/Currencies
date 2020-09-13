package com.example.currencies.ui.fragment

import com.example.currencies.domain.CurrenciesRepository
import com.example.currencies.ui.fragment.mvi.CurrenciesViewState
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import javax.inject.Inject

class CurrenciesFragmentPresenter @Inject constructor(private val repository: CurrenciesRepository) :
    MviBasePresenter<CurrenciesFragmentView, CurrenciesViewState>() {

    override fun bindIntents() {

    }

    private fun render(state: CurrenciesViewState) {

    }
}