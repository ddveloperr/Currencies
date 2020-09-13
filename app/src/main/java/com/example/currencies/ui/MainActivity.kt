package com.example.currencies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.currencies.ui.fragment.CurrenciesFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            addFragment()
        }
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, CurrenciesFragment(), null).commit()
    }
}