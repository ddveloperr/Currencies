package com.example.common.ext

import java.math.BigDecimal

fun BigDecimal?.getOrZero(): BigDecimal {
    return this ?: BigDecimal.ZERO
}