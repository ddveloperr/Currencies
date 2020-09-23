package com.example.common_ui.fragment

import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseView: MvpView {
    fun onError(t: Throwable)
}