package com.example.currencies.di.modules

import com.example.currencies.di.qualifier.CurrenciesHttpClient
import com.example.currencies.domain.ConfigUrls
import com.example.currencies.domain.api.CurrenciesApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideCurrenciesApi(@CurrenciesHttpClient okHttpClient: OkHttpClient): CurrenciesApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(ConfigUrls.REVOLUT_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(CurrenciesApi::class.java)
    }

    @Provides
    @Singleton
    @CurrenciesHttpClient
    fun provideCurrenciesHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder().build()
    }
}