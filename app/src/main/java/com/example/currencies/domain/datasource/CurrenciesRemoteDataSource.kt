package com.example.currencies.domain.datasource

import com.example.currencies.domain.api.CurrenciesApi
import com.example.currencies.domain.model.CurrenciesResponse
import io.reactivex.Single
import javax.inject.Inject

class CurrenciesRemoteDataSource @Inject constructor(private val api: CurrenciesApi) {
    fun getCurrencyRates(baseCurrency: String): Single<CurrenciesResponse> {
        return api.getCurrencyRates(baseCurrency)
    }
}