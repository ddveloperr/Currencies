package com.example.currencies.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.currencies.R

enum class CurrencyViewModel(
    @StringRes val nameRes: Int? = null,
    @DrawableRes val iconRes: Int? = null
) {
    CAD(R.string.currency_name_cad, R.drawable.ic_flag_canada),
    EUR(R.string.currency_name_eur, R.drawable.ic_flag_eu),
    SEK(R.string.currency_name_sek, R.drawable.ic_flag_sweden),
    USD(R.string.currency_name_usd, R.drawable.ic_flag_usa),
    DEFAULT
}

fun Currency.mapToViewModel(): CurrencyViewModel {
    return enumValues<CurrencyViewModel>().firstOrNull { it.name == this.name }
        ?: CurrencyViewModel.DEFAULT
}