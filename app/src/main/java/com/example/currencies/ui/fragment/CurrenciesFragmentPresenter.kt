package com.example.currencies.ui.fragment

import com.example.currencies.domain.CurrenciesRepository
import com.example.currencies.domain.model.Currency
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.currencies.ui.fragment.mvi.*
import com.example.mvi.MviPresenter
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import java.math.BigDecimal
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import com.example.common.ext.addSilentSubscription

class CurrenciesFragmentPresenter @Inject constructor(
    private val repository: CurrenciesRepository,
    useCase: CurrenciesUseCase,
    reducer: CurrenciesReducer
) : MviPresenter<CurrenciesFragmentView, CurrenciesViewAction, CurrenciesViewState, CurrenciesInitialState, CurrenciesPartialState, CurrenciesSubscriptions>(
    reducer,
    useCase
) {

    companion object {
        private const val UPDATE_INTERVAL_SEC = 2L
    }

    private val defaultMultiplicator = BigDecimal.valueOf(100)

    private var currencyUpdateDisposable: Disposable? = null

    override val infoDataSubject: BehaviorSubject<CurrenciesInitialState> =
        BehaviorSubject.createDefault(
            CurrenciesInitialState(Currency.USD, defaultMultiplicator)
        )

    override fun init() {
        super.init()
        viewActionSubject.onNext(CurrenciesViewAction.StartFirstLoad)
    }

    override fun getInitialViewState(): CurrenciesViewState {
        return CurrenciesViewState(isLoading = true)
    }

    fun onItemClicked(item: CurrencyViewHolderItem) {
        viewActionSubject.onNext(CurrenciesViewAction.OnItemClicked(item))
    }

    fun onEditValueChanged(item: CurrencyViewHolderItem, value: BigDecimal?) {
        viewActionSubject.onNext(CurrenciesViewAction.OnEditValueChanged(item, value))
    }

    override fun renderSubscriptionEvent(subscriptionEvent: CurrenciesSubscriptions) {
        when (subscriptionEvent) {
            is CurrenciesSubscriptions.StartRateUpdate -> startCurrencyUpdate(subscriptionEvent.baseCurrency)
            CurrenciesSubscriptions.StopRateUpdate -> cancelCurrencyUpdate()
        }
    }

    override fun render(state: CurrenciesViewState) {
        state.data?.initialState?.let {
            infoDataSubject.onNext(it)
        }
        when {
            state.isLoading -> {
                ifViewAttached { it.showProgressBar() }
            }
            state.error != null -> {
                ifViewAttached { it.hideProgressBar() }
            }
            state.data != null -> {
                ifViewAttached {
                    it.hideProgressBar()
                    it.render(state.data.getList())
                }
            }
        }
    }

    override fun unsubscribe() {
        super.unsubscribe()
        cancelCurrencyUpdate()
    }

    private fun startCurrencyUpdate(baseCurrency: Currency) {
        cancelCurrencyUpdate()
        currencyUpdateDisposable = addSilentSubscription(
            Observable.interval(UPDATE_INTERVAL_SEC, TimeUnit.SECONDS).flatMapSingle {
                repository.getCurrencyRates(baseCurrency.name)
            }, onNext = {
                viewActionSubject.onNext(CurrenciesViewAction.OnCurrencyRateUpdate(it))
            })
    }

    private fun cancelCurrencyUpdate() {
        if (currencyUpdateDisposable != null && !currencyUpdateDisposable!!.isDisposed) {
            currencyUpdateDisposable!!.dispose()
        }
    }

}