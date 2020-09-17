package com.example.mvi

import com.example.mvi.action.MviNotInterruptedAction
import io.reactivex.Observable

fun <A, IS, PS> Observable<Pair<A, IS>>.switchMapOrFlatMap(
    function: (Pair<A, IS>) -> Observable<PS>
): Observable<PS> {
    return this.publish { pair ->
        Observable.merge(
            pair.filter { it.first is MviNotInterruptedAction }
                .flatMap { function(it) },
            pair.filter { it.first !is MviNotInterruptedAction }
                .switchMap { function(it) }
        )
    }
}