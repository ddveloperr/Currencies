package com.example.common.utils

import android.content.Context
import androidx.fragment.app.FragmentActivity

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