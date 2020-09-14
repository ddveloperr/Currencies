package com.example.mvi

import com.example.mvi.state.MviPartialState
import com.example.mvi.state.MviViewState

interface MviReducer<S : MviViewState, PS : MviPartialState> {
    fun reduce(previousState: S, change: PS): S
}