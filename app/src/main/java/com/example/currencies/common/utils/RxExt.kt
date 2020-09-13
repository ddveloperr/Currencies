package com.example.currencies.common.utils

import io.reactivex.Observable

fun <T> T?.toObservable(): Observable<T> {
    return if (this == null) {
        Observable.empty()
    } else {
        Observable.just(this)
    }
}