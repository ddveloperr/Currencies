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
import com.example.common_ui.ext.addSilentSubscription
import com.example.currencies.di.qualifier.CurrenciesDefaultCurrency
import com.example.currencies.di.qualifier.CurrenciesDefaultMultiplicator
import com.example.currencies.di.qualifier.CurrenciesUpdateInterval

class CurrenciesFragmentPresenter @Inject constructor(
    private val repository: CurrenciesRepository,
    @CurrenciesUpdateInterval
    private val updateInterval: BigDecimal,
    @CurrenciesDefaultMultiplicator
    private val defaultMultiplicator: BigDecimal,
    @CurrenciesDefaultCurrency
    private val defaultCurrency: Currency,
    useCase: CurrenciesUseCase,
    reducer: CurrenciesReducer
) : MviPresenter<CurrenciesFragmentView, CurrenciesViewAction, CurrenciesViewState, CurrenciesInitialState, CurrenciesPartialState, CurrenciesSubscriptions>(
    reducer,
    useCase
) {

    private var currencyUpdateDisposable: Disposable? = null

    override val infoDataSubject: BehaviorSubject<CurrenciesInitialState> =
        BehaviorSubject.createDefault(
            CurrenciesInitialState(defaultCurrency, defaultMultiplicator)
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
            is CurrenciesSubscriptions.StopRateUpdate -> cancelCurrencyUpdate()
            is CurrenciesSubscriptions.ScrollToTop -> scrollToTop()
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
                ifViewAttached {
                    it.hideProgressBar()
                    it.onError(state.error)
                }
            }
            state.data != null -> {
                ifViewAttached {
                    it.hideProgressBar()
                    it.render(state.data.getList())
                }
            }
        }
    }

    private fun scrollToTop() {
        ifViewAttached { it.scrollToTop() }
    }

    override fun unsubscribe() {
        super.unsubscribe()
        cancelCurrencyUpdate()
    }

    private fun startCurrencyUpdate(baseCurrency: Currency) {
        cancelCurrencyUpdate()
        currencyUpdateDisposable = addSilentSubscription(
            Observable.interval(updateInterval.toLong(), TimeUnit.SECONDS).flatMapSingle {
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