package com.example.currencies.domain.model

import java.io.Serializable
import java.math.BigDecimal

data class CurrenciesResponse(val baseCurrency: String, val currencyRates: Map<Currency, BigDecimal>) :
    Serializable