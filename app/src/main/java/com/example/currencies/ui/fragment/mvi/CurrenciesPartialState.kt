package com.example.currencies.ui.fragment.mvi

import com.example.currencies.domain.model.CurrenciesResponse
import com.example.mvi.state.MviPartialState

sealed class CurrenciesPartialState: MviPartialState {
    object Loading : CurrenciesPartialState()
    data class DataLoaded(val data: CurrenciesResponse) : CurrenciesPartialState()
    data class FirstLoadError(val throwable: Throwable) : CurrenciesPartialState()
}