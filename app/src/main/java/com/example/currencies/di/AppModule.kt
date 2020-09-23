package com.example.currencies.di

import com.example.currencies.di.modules.NetworkModule
import com.example.error_manager.handler.DialogHandler
import com.example.error_manager.handler.ErrorHandler
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