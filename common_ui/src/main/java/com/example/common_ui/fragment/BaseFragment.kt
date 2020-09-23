package com.example.common_ui.fragment

import com.example.error_manager.utils.ErrorUtils
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.hannesdorfmann.mosby3.mvp.MvpPresenter

abstract class BaseFragment<V : BaseView, P : MvpPresenter<V>> : MvpFragment<V, P>(), BaseView {

    override fun onError(t: Throwable) {
        ErrorUtils.onError(t, requireContext(), requireActivity())
    }
}