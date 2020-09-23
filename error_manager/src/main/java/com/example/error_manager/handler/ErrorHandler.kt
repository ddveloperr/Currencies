package com.example.error_manager.handler

import android.content.Context
import androidx.fragment.app.FragmentActivity

class ErrorHandler(private val dialogHandler: DialogHandler) {

    fun onError(t: Throwable, context: Context, activity: FragmentActivity) {
        dialogHandler.showDialog(t, activity)
    }
}