package com.example.currencies.domain.model

import java.io.Serializable

data class LatestResponse(val baseCurrency: String, val rates: Rates) : Serializable