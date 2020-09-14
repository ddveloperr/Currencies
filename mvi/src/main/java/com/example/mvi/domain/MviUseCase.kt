package com.example.mvi.domain

import com.example.mvi.action.MviAction
import com.example.mvi.state.MviInitialState
import com.example.mvi.state.MviPartialState
import com.example.mvi.MviSubscriptions
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class MviUseCase<A : MviAction, IS : MviInitialState, P : MviPartialState, S : MviSubscriptions> {

    val subscriptionsSubject = PublishSubject.create<S>()

    abstract fun onAction(action: A, state: IS): Observable<P>
}