package com.example.currencies.ui.fragment.mvi

import com.example.currencies.domain.model.CurrenciesResponse
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.mvi.state.MviPartialState
import java.math.BigDecimal

sealed class CurrenciesPartialState: MviPartialState {
    object Loading : CurrenciesPartialState()
    data class DataLoaded(val data: CurrenciesResponse, val multiplicator: BigDecimal?) : CurrenciesPartialState()
    data class OnItemClicked(val item: CurrencyViewHolderItem): CurrenciesPartialState()
    data class OnCurrencyRateUpdate(val currenciesResponse: CurrenciesResponse): CurrenciesPartialState()
    data class FirstLoadError(val throwable: Throwable) : CurrenciesPartialState()
    data class OnEditValueChanged(val item: CurrencyViewHolderItem, val value: BigDecimal?): CurrenciesPartialState()
    object Empty: CurrenciesPartialState()
}