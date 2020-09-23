package com.example.common.model

import android.content.Context
import androidx.annotation.StringRes

sealed class StringSource {
    abstract fun getText(context: Context): String

    class Text(private val value: String) : StringSource() {
        override fun getText(context: Context): String = value
    }

    class Resource(@StringRes private val value: Int) : StringSource() {
        override fun getText(context: Context): String = context.getString(value)
    }

    object Empty : StringSource() {
        override fun getText(context: Context): String = ""
    }
}