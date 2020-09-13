package com.example.currencies.di.modules

import com.example.currencies.di.scope.FragmentScope
import com.example.currencies.ui.CurrenciesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributeFirstFragmentInjector(): CurrenciesFragment
}