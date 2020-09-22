package com.example.common.utils

import androidx.fragment.app.FragmentActivity

class DialogHandler {

    fun showDialog(t: Throwable, activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction()
            .add(ErrorDialogFragment.newInstance(t.message), ErrorDialogFragment.TAG)
            .commitAllowingStateLoss()
    }
}