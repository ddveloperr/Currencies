package com.example.common.ext

import android.text.Editable
import java.math.BigDecimal

fun Editable?.toBigDecimal(): BigDecimal? {
    return if (!this.isNullOrEmpty()) BigDecimal(this.toString()) else null
}