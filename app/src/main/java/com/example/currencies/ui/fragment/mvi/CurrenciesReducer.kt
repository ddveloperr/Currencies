package com.example.currencies.ui.fragment.mvi

import javax.inject.Inject

class CurrenciesReducer @Inject constructor() {

    fun reduce(
        partialState: CurrenciesPartialState,
        previousState: CurrenciesViewState
    ): CurrenciesViewState {
        return when (partialState) {
            is CurrenciesPartialState.Loading -> reduceLoadingState(partialState, previousState)
            is CurrenciesPartialState.DataLoaded -> reduceDataLoadedState(
                partialState,
                previousState
            )
            is CurrenciesPartialState.FirstLoadError -> reduceFirstLoadError(
                partialState,
                previousState
            )
        }
    }

    private fun reduceLoadingState(
        partialState: CurrenciesPartialState.Loading,
        previousState: CurrenciesViewState
    ): CurrenciesViewState {
        return previousState
    }

    private fun reduceDataLoadedState(
        partialState: CurrenciesPartialState.DataLoaded,
        previousState: CurrenciesViewState
    ): CurrenciesViewState {
        return previousState
    }

    private fun reduceFirstLoadError(
        partialState: CurrenciesPartialState.FirstLoadError,
        previousState: CurrenciesViewState
    ): CurrenciesViewState {
        return previousState
    }
}