package com.example.common.ext

inline fun addDefaultUncaughtExceptionHandler(crossinline uncaughtExceptionHandler: (Thread, error: Throwable) -> Unit) {
    val currentUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
    Thread.setDefaultUncaughtExceptionHandler { thread, error ->
        uncaughtExceptionHandler.invoke(thread, error)
        currentUncaughtExceptionHandler!!.uncaughtException(thread, error)
    }
}