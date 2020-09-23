package com.example.currencies.di.modules

import com.example.currencies.di.qualifier.CurrenciesDefaultCurrency
import com.example.currencies.di.qualifier.CurrenciesDefaultMultiplicator
import com.example.currencies.di.qualifier.CurrenciesUpdateInterval
import com.example.currencies.di.scope.FragmentScope
import com.example.currencies.domain.model.Currency
import dagger.Module
import dagger.Provides
import java.math.BigDecimal

@Module
class CurrenciesFragmentModule {

    @Provides
    @FragmentScope
    @CurrenciesUpdateInterval
    fun provideCurrenciesUpdateInterval(): BigDecimal {
        return BigDecimal.valueOf(1)
    }

    @Provides
    @FragmentScope
    @CurrenciesDefaultMultiplicator
    fun provideCurrenciesDefaultMultiplicator(): BigDecimal {
        return BigDecimal.valueOf(100)
    }

    @Provides
    @FragmentScope
    @CurrenciesDefaultCurrency
    fun provideCurrenciesDefaultCurrency(): Currency {
        return Currency.USD
    }
}