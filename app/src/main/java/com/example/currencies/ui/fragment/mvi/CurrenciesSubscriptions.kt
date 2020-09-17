package com.example.currencies.ui.fragment.mvi

import com.example.currencies.domain.model.Currency
import com.example.mvi.MviSubscriptions

sealed class CurrenciesSubscriptions : MviSubscriptions {
    data class StartRateUpdate(val baseCurrency: Currency) : CurrenciesSubscriptions()
}