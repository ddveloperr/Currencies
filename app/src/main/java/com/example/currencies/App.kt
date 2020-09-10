package com.example.currencies

import android.app.Application
import com.example.currencies.di.DaggerAppComponent
import com.example.currencies.utils.addDefaultUncaughtExceptionHandler
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build().inject(this)
        initErrorHandler()
    }

    private fun initErrorHandler() {
        rxJavaErrorHandler()
        defaultThreadErrorHandler()
    }

    private fun rxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            // todo
        }
    }

    private fun defaultThreadErrorHandler() {
        addDefaultUncaughtExceptionHandler { _, error ->
            // todo
        }
    }
}