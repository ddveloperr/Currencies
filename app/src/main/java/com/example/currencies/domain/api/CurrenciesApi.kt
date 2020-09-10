package com.example.currencies.domain.api

import com.example.currencies.domain.model.CurrenciesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {

    @GET("latest")
    fun getCurrencyRates(@Query("base") baseCurrency: String): Single<CurrenciesResponse>
}