package com.example.currencies.ui.fragment.mvi

import com.example.currencies.domain.model.Currency
import com.example.mvi.state.MviInitialState

data class CurrenciesInitialState(val baseCurrency: Currency): MviInitialState