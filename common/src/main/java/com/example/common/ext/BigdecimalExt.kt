package com.example.common.ext

import java.math.BigDecimal

fun BigDecimal?.valueOrZero(): BigDecimal {
    return this ?: BigDecimal.ZERO
}