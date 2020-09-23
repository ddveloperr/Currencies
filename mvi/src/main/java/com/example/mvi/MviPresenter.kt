package com.example.mvi

import com.example.common_ui.fragment.BaseMvpPresenter
import com.example.mvi.action.MviAction
import com.example.mvi.domain.MviUseCase
import com.example.mvi.state.MviInitialState
import com.example.mvi.state.MviPartialState
import com.example.mvi.state.MviViewState
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import com.example.common_ui.ext.addAndReturnSubscription

abstract class MviPresenter<V : MviView, A : MviAction, VS : MviViewState, IS : MviInitialState, PS : MviPartialState, S : MviSubscriptions>(
    protected val reducer: MviReducer<VS, PS>,
    protected val useCase: MviUseCase<A, IS, PS, S>
) : BaseMvpPresenter<V>() {

    open val viewActionSubject = BehaviorSubject.create<A>()
    open val infoDataSubject = BehaviorSubject.create<IS>()
    open var latestViewState: VS? = null
    protected var subscriptionDisposable: Disposable? = null
    protected var actionDisposable: Disposable? = null
    private val initialState: VS by lazy { getInitialViewState() }

    abstract fun getInitialViewState(): VS

    open fun init() {
        bindActions()
        bindUseCaseSubscriptions()
    }

    protected open fun bindUseCaseSubscriptions() {
        subscriptionDisposable?.dispose()
        subscriptionDisposable = addAndReturnSubscription(
            useCase.subscriptionsSubject,
            onNext = ::renderSubscriptionEvent
        )
    }

    protected open fun renderSubscriptionEvent(subscriptionEvent: S) = Unit

    protected open fun bindActions() {
        actionDisposable?.dispose()
        actionDisposable = addAndReturnSubscription(
            viewActionSubject
                .observeOn(Schedulers.io())
                .distinctUntilChanged()
                .withLatestFrom(infoDataSubject, ::Pair)
                .flatMap { (action, initialData) ->
                    useCase.onAction(action, initialData)
                }
                .scan(initialState, reducer::reduce),
            onNext = { viewState ->
                latestViewState = viewState
                render(viewState)
            }
        )
    }

    abstract fun render(state: VS)

    override fun detachView() {
        subscriptionDisposable?.dispose()
        actionDisposable?.dispose()
        super.detachView()
    }
}