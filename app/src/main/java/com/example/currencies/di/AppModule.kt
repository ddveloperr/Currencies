package com.example.currencies.di

import com.example.currencies.di.modules.NetworkModule
import dagger.Module

@Module(includes = [NetworkModule::class])
class AppModule