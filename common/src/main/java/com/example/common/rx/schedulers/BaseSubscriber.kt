package com.example.common.rx.schedulers

import io.reactivex.observers.DisposableObserver

open class BaseSubscriber<T> : DisposableObserver<T>() {
    public override fun onStart() = Unit
    override fun onComplete() = Unit
    override fun onError(e: Throwable) = Unit
    override fun onNext(t: T) = Unit
}