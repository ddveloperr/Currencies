package com.example.currencies.ui.fragment.adapter

import androidx.annotation.DrawableRes
import com.example.common.model.StringSource
import java.io.Serializable
import java.math.BigDecimal

data class CurrencyViewHolderItem(
    val title: StringSource,
    val subtitle: StringSource?,
    @DrawableRes
    val icon: Int?,
    val value: BigDecimal
) : Serializable