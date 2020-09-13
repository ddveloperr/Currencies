package com.example.currencies.common.utils

inline fun <T> lazyNone(noinline initializer: () -> T) =
    lazy(LazyThreadSafetyMode.NONE, initializer)