package com.example.currencies.di.modules

import com.example.currencies.di.scope.FragmentScope
import com.example.currencies.ui.fragment.CurrenciesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector(modules = [CurrenciesFragmentModule::class])
    @FragmentScope
    abstract fun contributeCurrenciesFragmentInjector(): CurrenciesFragment
}