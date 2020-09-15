package com.example.currencies.ui.fragment.mvi

import com.example.currencies.domain.model.Currency
import com.example.mvi.state.MviInitialState
import java.math.BigDecimal

data class CurrenciesInitialState(val baseCurrency: Currency, val multiplicator: BigDecimal?): MviInitialState