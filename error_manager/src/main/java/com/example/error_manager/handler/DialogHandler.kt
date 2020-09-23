package com.example.error_manager.handler

import androidx.fragment.app.FragmentActivity
import com.example.error_manager.fragment.ErrorDialogFragment

class DialogHandler {

    fun showDialog(t: Throwable, activity: FragmentActivity) {
        val fragmentManager = activity.supportFragmentManager
        fragmentManager.beginTransaction()
            .add(ErrorDialogFragment.newInstance(t.message), ErrorDialogFragment.TAG)
            .commitAllowingStateLoss()
    }
}