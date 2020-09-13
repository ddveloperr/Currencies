package com.example.currencies.ui.fragment

import com.example.currencies.ui.fragment.mvi.CurrenciesReducer
import com.example.currencies.ui.fragment.mvi.CurrenciesUseCase
import com.example.currencies.ui.fragment.mvi.CurrenciesViewAction
import com.example.currencies.ui.fragment.mvi.CurrenciesViewState
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class CurrenciesFragmentPresenter @Inject constructor(
    private val useCase: CurrenciesUseCase,
    private val reducer: CurrenciesReducer
) : MviBasePresenter<CurrenciesFragmentView, CurrenciesViewState>() {

    private val viewActionSubject = BehaviorSubject.create<CurrenciesViewAction>()

    fun init() {
        viewActionSubject.onNext(CurrenciesViewAction.StartFirstLoad)
    }

    override fun bindIntents() {
        subscribeViewState(
            viewActionSubject.subscribeOn(Schedulers.io()).distinctUntilChanged().flatMap {
                useCase.onAction(it)
            }.scan(CurrenciesViewState(), reducer::reduce)
                .observeOn(AndroidSchedulers.mainThread()), CurrenciesFragmentView::render
        )
    }
}