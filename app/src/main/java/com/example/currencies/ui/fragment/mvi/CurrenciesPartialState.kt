package com.example.currencies.ui.fragment.mvi

import com.example.currencies.domain.model.CurrenciesResponse

sealed class CurrenciesPartialState {
    object Loading : CurrenciesPartialState()
    data class DataLoaded(val data: CurrenciesResponse) : CurrenciesPartialState()
    data class FirstLoadError(val throwable: Throwable) : CurrenciesPartialState()
}