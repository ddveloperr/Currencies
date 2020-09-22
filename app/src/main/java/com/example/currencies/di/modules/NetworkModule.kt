package com.example.currencies.di.modules

import com.example.currencies.di.qualifier.CurrenciesHttpClient
import com.example.currencies.domain.ConfigUrls
import com.example.currencies.domain.api.CurrenciesApi
import com.example.currencies.domain.api.gson.CurrenciesResponseGsonConverter
import com.example.currencies.domain.model.CurrenciesResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
    fun provideCurrenciesApi(
        @CurrenciesHttpClient okHttpClient: OkHttpClient,
        gson: Gson
    ): CurrenciesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(ConfigUrls.REVOLUT_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(CurrenciesApi::class.java)
    }

    @Provides
    @Singleton
    @CurrenciesHttpClient
    fun provideCurrenciesHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(
            CurrenciesResponse::class.java,
            CurrenciesResponseGsonConverter()
        )
        return builder.create()
    }
}