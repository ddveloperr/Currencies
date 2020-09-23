package com.example.error_manager.utils

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.example.error_manager.handler.ErrorHandler

object ErrorUtils {

    var errorHandler: ErrorHandler? = null

    fun onError(t: Throwable, context: Context, activity: FragmentActivity) {
        errorHandler?.onError(t, context, activity)
    }
}

inline fun addDefaultUncaughtExceptionHandler(crossinline uncaughtExceptionHandler: (Thread, error: Throwable) -> Unit) {
    val currentUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
    Thread.setDefaultUncaughtExceptionHandler { thread, error ->
        uncaughtExceptionHandler.invoke(thread, error)
        currentUncaughtExceptionHandler!!.uncaughtException(thread, error)
    }
}