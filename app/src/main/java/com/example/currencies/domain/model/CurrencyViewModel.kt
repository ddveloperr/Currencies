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
    AUD(R.string.currency_name_aud),
    BGN(R.string.currency_name_bgn),
    BRL(R.string.currency_name_brl),
    CHF(R.string.currency_name_chf),
    CNY(R.string.currency_name_cny),
    CZK(R.string.currency_name_czk),
    DKK(R.string.currency_name_dkk),
    GBP(R.string.currency_name_gbp),
    HKD(R.string.currency_name_hkd),
    HRK(R.string.currency_name_hrk),
    HUF(R.string.currency_name_huf),
    IDR(R.string.currency_name_idr),
    ILS(R.string.currency_name_ils),
    INR(R.string.currency_name_inr),
    ISK(R.string.currency_name_isk),
    JPY(R.string.currency_name_jpy),
    KRW(R.string.currency_name_krw),
    MXN(R.string.currency_name_mxn),
    MYR(R.string.currency_name_myr),
    NOK(R.string.currency_name_nok),
    NZD(R.string.currency_name_nzd),
    PHP(R.string.currency_name_php),
    PLN(R.string.currency_name_pln),
    RON(R.string.currency_name_ron),
    RUB(R.string.currency_name_rub),
    SGD(R.string.currency_name_sgd),
    THB(R.string.currency_name_thb),
    ZAR(R.string.currency_name_zar),
    DEFAULT
}

fun Currency.mapToViewModel(): CurrencyViewModel {
    return enumValues<CurrencyViewModel>().firstOrNull { it.name == this.name }
        ?: CurrencyViewModel.DEFAULT
}