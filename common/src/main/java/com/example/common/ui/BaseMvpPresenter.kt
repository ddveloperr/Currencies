package com.example.common.ui

import com.example.common.rx.schedulers.BaseSubscriber
import com.example.common.rx.subscribers.ObservableSubscribers
import com.example.common.rx.schedulers.BaseSingleSubscriber
import com.example.common.rx.subscribers.DefaultSubscribers
import com.example.common.rx.subscribers.SingleSchedulers
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseMvpPresenter<V: MvpView>: MvpBasePresenter<V>() {

    private val compositeDisposable = CompositeDisposable()

    fun <T> addSubscription(
        observable: Observable<T>,
        subscriber: BaseSubscriber<T>
    ): Disposable {
        val disposable = observable
            .compose(ObservableSubscribers())
            .subscribeWith(subscriber)
        compositeDisposable.add(disposable)
        return disposable
    }

    fun <T> addSubscription(
        single: Single<T>,
        subscriber: BaseSingleSubscriber<T>
    ): Disposable {
        val disposable = single
            .compose(SingleSchedulers())
            .subscribeWith(subscriber)
        compositeDisposable.add(disposable)
        return disposable
    }

    fun <T> addAndReturnSubscription(
        observable: Observable<T>,
        subscriber: BaseSubscriber<T>
    ): Disposable {
        return addSilentSubscription(observable, subscriber)
    }

    fun <T> addSilentSubscription(
        observable: Observable<T>,
        subscriber: BaseSubscriber<T> = object : BaseSubscriber<T>() {}
    ): Disposable {
        val disposable = observable
            .compose(DefaultSubscribers())
            .subscribeWith(subscriber)
        compositeDisposable.add(disposable)
        return disposable
    }

    fun <T> addAndReturnSubscription(
        single: Single<T>,
        subscriber: BaseSingleSubscriber<T>
    ): Disposable {
        return addSilentSubscription(single, subscriber)
    }

    fun <T> addSilentSubscription(
        single: Single<T>,
        subscriber: BaseSingleSubscriber<T>
    ): Disposable {
        val disposable = single
            .compose(SingleSchedulers())
            .subscribeWith(subscriber)
        compositeDisposable.add(disposable)
        return disposable
    }

    override fun destroy() {
        unsubscribe()
        super.destroy()
    }

    protected open fun unsubscribe() {
        compositeDisposable.clear()
    }
}