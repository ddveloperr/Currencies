package com.example.common.ext

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <T> lazyNone(noinline initializer: () -> T) =
    lazy(LazyThreadSafetyMode.NONE, initializer)

inline fun <reified T : Fragment> newInstance(
    noinline populator: (Bundle.() -> Unit)? = null
): T {
    return T::class.java.newInstance().apply {
        populator?.let { arguments = Bundle().apply(it) }
    }
}