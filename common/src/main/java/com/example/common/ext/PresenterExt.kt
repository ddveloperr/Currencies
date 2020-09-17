package com.example.common.ext

import com.example.common.rx.schedulers.BaseSingleSubscriber
import com.example.common.rx.schedulers.BaseSubscriber
import com.example.common.ui.BaseMvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

inline fun <T : Any, V : MvpView> BaseMvpPresenter<V>.addSubscription(
    observable: Observable<T>,
    crossinline onStart: () -> Unit = {},
    crossinline onNext: (T) -> Unit = {},
    crossinline onComplete: () -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {}
): Disposable = addSubscription(observable, object : BaseSubscriber<T>() {
    override fun onComplete() = onComplete()
    override fun onError(e: Throwable) = onError(e)
    override fun onNext(t: T) = onNext(t)
    override fun onStart() = onStart()
})

inline fun <T : Any, V : MvpView> BaseMvpPresenter<V>.addSubscription(
    single: Single<T>,
    crossinline onStart: () -> Unit = {},
    crossinline onSuccess: (T) -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {}
): Disposable = addSubscription(single, object : BaseSingleSubscriber<T>() {
    override fun onError(e: Throwable) = onError(e)
    override fun onSuccess(t: T) = onSuccess(t)
    override fun onStart() = onStart()
})

inline fun <T : Any, V : MvpView> BaseMvpPresenter<V>.addAndReturnSubscription(
    observable: Observable<T>,
    crossinline onStart: () -> Unit = {},
    crossinline onNext: (T) -> Unit = {},
    crossinline onComplete: () -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {}
): Disposable = addAndReturnSubscription(
    observable,
    object : BaseSubscriber<T>() {
        override fun onComplete() = onComplete()
        override fun onError(e: Throwable) = onError(e)
        override fun onNext(t: T) = onNext(t)
        override fun onStart() = onStart()
    }
)

inline fun <T : Any, V : MvpView> BaseMvpPresenter<V>.addAndReturnSubscription(
    single: Single<T>,
    crossinline onStart: () -> Unit = {},
    crossinline onSuccess: (T) -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {}
): Disposable = addAndReturnSubscription(
    single,
    object : BaseSingleSubscriber<T>() {
        override fun onError(e: Throwable) = onError(e)
        override fun onSuccess(t: T) = onSuccess(t)
        override fun onStart() = onStart()
    }
)

inline fun <T : Any, V : MvpView> BaseMvpPresenter<V>.addSilentSubscription(
    observable: Observable<T>,
    crossinline onStart: () -> Unit = {},
    crossinline onNext: (T) -> Unit = {},
    crossinline onComplete: () -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {}
): Disposable = addSilentSubscription(
    observable,
    object : BaseSubscriber<T>() {
        override fun onComplete() = onComplete()
        override fun onError(e: Throwable) = onError(e)
        override fun onNext(t: T) = onNext(t)
        override fun onStart() = onStart()
    }
)