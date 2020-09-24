package com.example.currencies.di.modules.network

import dagger.Module

@Module(includes = [CurrenciesNetworkModule::class, JsonModule::class])
class CommonNetworkModule