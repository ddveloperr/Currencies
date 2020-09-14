package com.example.common.rx.schedulers

import io.reactivex.observers.DisposableSingleObserver

open class BaseSingleSubscriber<T> : DisposableSingleObserver<T>() {
    public override fun onStart() = Unit
    override fun onSuccess(t: T) = Unit
    override fun onError(e: Throwable) = Unit
}