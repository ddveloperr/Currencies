package com.example.currencies.di

import com.example.common.utils.DialogHandler
import com.example.common.utils.ErrorHandler
import com.example.currencies.di.modules.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {
    @Provides
    @Singleton
    fun provideErrorHandler(dialogHandler: DialogHandler): ErrorHandler {
        return ErrorHandler(dialogHandler)
    }

    @Provides
    @Singleton
    fun provideDialogHandler(): DialogHandler {
        return DialogHandler()
    }
}