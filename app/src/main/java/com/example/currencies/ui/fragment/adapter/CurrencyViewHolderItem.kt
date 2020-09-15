package com.example.currencies.ui.fragment.adapter

import androidx.annotation.DrawableRes
import com.example.common.model.StringSource
import com.example.currencies.domain.model.Currency
import java.io.Serializable
import java.math.BigDecimal

data class CurrencyViewHolderItem(
    val title: StringSource,
    val subtitle: StringSource?,
    @DrawableRes
    val icon: Int?,
    val rate: BigDecimal,
    val multiplicator: BigDecimal?,
    val currency: Currency,
    val isBaseCurrency: Boolean = false
) : Serializable