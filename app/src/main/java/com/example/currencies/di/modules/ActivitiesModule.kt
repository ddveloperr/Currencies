package com.example.currencies.di.modules

import com.example.currencies.di.scope.ActivityScope
import com.example.currencies.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    @ActivityScope
    abstract fun contributeMainActivityInjector(): MainActivity
}