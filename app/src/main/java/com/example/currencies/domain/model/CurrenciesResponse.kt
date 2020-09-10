package com.example.currencies.domain.model

import java.io.Serializable

data class CurrenciesResponse(val baseCurrency: String, val currencyRates: List<CurrencyRate>) :
    Serializable