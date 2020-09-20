package com.example.currencies.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.currencies.R

enum class CurrencyViewModel(
    @StringRes val nameRes: Int? = null,
    @DrawableRes val iconRes: Int? = null
) {
    AUD(R.string.currency_name_aud, R.drawable.ic_currency_aud),
    BGN(R.string.currency_name_bgn, R.drawable.ic_currency_bgn),
    BRL(R.string.currency_name_brl, R.drawable.ic_currency_brl),
    CAD(R.string.currency_name_cad, R.drawable.ic_currency_cad),
    CHF(R.string.currency_name_chf, R.drawable.ic_currency_chf),
    CNY(R.string.currency_name_cny, R.drawable.ic_currency_cny),
    CZK(R.string.currency_name_czk, R.drawable.ic_currency_czk),
    DKK(R.string.currency_name_dkk, R.drawable.ic_currency_dkk),
    EUR(R.string.currency_name_eur, R.drawable.ic_currency_eur),
    GBP(R.string.currency_name_gbp, R.drawable.ic_currency_gbp),
    HKD(R.string.currency_name_hkd, R.drawable.ic_currency_hkd),
    HRK(R.string.currency_name_hrk, R.drawable.ic_currency_hrk),
    HUF(R.string.currency_name_huf, R.drawable.ic_currency_huf),
    IDR(R.string.currency_name_idr, R.drawable.ic_currency_idr),
    ILS(R.string.currency_name_ils, R.drawable.ic_currency_ils),
    INR(R.string.currency_name_inr, R.drawable.ic_currency_inr),
    ISK(R.string.currency_name_isk, R.drawable.ic_currency_isk),
    JPY(R.string.currency_name_jpy, R.drawable.ic_currency_jpy),
    KRW(R.string.currency_name_krw, R.drawable.ic_currency_krw),
    MXN(R.string.currency_name_mxn, R.drawable.ic_currency_mxn),
    MYR(R.string.currency_name_myr, R.drawable.ic_currency_myr),
    NOK(R.string.currency_name_nok, R.drawable.ic_currency_nok),
    NZD(R.string.currency_name_nzd, R.drawable.ic_currency_nzd),
    PHP(R.string.currency_name_php, R.drawable.ic_currency_php),
    PLN(R.string.currency_name_pln, R.drawable.ic_currency_pln),
    RON(R.string.currency_name_ron, R.drawable.ic_currency_ron),
    RUB(R.string.currency_name_rub, R.drawable.ic_currency_rub),
    SEK(R.string.currency_name_sek, R.drawable.ic_currency_sek),
    SGD(R.string.currency_name_sgd, R.drawable.ic_currency_sgd),
    THB(R.string.currency_name_thb, R.drawable.ic_currency_thb),
    USD(R.string.currency_name_usd, R.drawable.ic_currency_usd),
    ZAR(R.string.currency_name_zar, R.drawable.ic_currency_zar),
    DEFAULT
}

fun Currency.mapToViewModel(): CurrencyViewModel {
    return enumValues<CurrencyViewModel>().firstOrNull { it.name == this.name }
        ?: CurrencyViewModel.DEFAULT
}