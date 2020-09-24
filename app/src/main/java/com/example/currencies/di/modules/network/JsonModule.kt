package com.example.currencies.di.modules.network

import com.example.currencies.domain.api.gson.CurrenciesResponseGsonConverter
import com.example.currencies.domain.model.CurrenciesResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JsonModule {

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