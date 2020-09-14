package com.example.common.ext

inline fun <T> lazyNone(noinline initializer: () -> T) =
    lazy(LazyThreadSafetyMode.NONE, initializer)