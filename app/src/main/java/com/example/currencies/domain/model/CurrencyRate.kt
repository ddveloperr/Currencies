package com.example.currencies.domain.model

import java.math.BigDecimal

data class CurrencyRate(val currency: Currency, val rate: BigDecimal)