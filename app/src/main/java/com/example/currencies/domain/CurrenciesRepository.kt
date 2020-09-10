package com.example.currencies.domain

import com.example.currencies.domain.datasource.CurrenciesRemoteDataSource
import com.example.currencies.domain.model.LatestResponse
import io.reactivex.Single
import javax.inject.Inject

class CurrenciesRepository @Inject constructor(private val remote: CurrenciesRemoteDataSource) {
    fun getCurrencyRates(baseCurrency: String): Single<LatestResponse> {
        return remote.getCurrencyRates(baseCurrency)
    }
}