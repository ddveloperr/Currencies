package com.example.common.ui

import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseView: MvpView {
    fun onError(t: Throwable)
}