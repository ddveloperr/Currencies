package com.example.common.utils

import android.content.Context
import androidx.fragment.app.FragmentActivity

class ErrorHandler(private val dialogHandler: DialogHandler) {

    fun onError(t: Throwable, context: Context, activity: FragmentActivity) {
        dialogHandler.showDialog(t, activity)
    }
}