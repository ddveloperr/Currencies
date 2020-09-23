package com.example.currencies

import android.app.Application
import com.example.currencies.di.DaggerAppComponent
import com.example.error_manager.handler.ErrorHandler
import com.example.error_manager.utils.addDefaultUncaughtExceptionHandler
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var errorHandler: ErrorHandler

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build().inject(this)
        initErrorHandler()
    }

    private fun initErrorHandler() {
        com.example.error_manager.utils.ErrorUtils.errorHandler = errorHandler
        rxJavaErrorHandler()
        defaultThreadErrorHandler()
    }

    private fun rxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            // todo add global error log handler (Crashlytics/Amplitude)
        }
    }

    private fun defaultThreadErrorHandler() {
        addDefaultUncaughtExceptionHandler { _, error ->
            // todo
        }
    }
}