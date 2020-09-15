package com.example.currencies.ui.fragment

import com.example.currencies.domain.model.Currency
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.example.currencies.ui.fragment.mvi.*
import com.example.mvi.MviPresenter
import com.example.mvi.MviSubscriptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.math.BigDecimal
import javax.inject.Inject

class CurrenciesFragmentPresenter @Inject constructor(
    useCase: CurrenciesUseCase,
    reducer: CurrenciesReducer
) : MviPresenter<CurrenciesFragmentView, CurrenciesViewAction, CurrenciesViewState, CurrenciesInitialState, CurrenciesPartialState, MviSubscriptions>(
    reducer,
    useCase
) {

    private val defaultMultiplicator = BigDecimal.valueOf(100)

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

    override fun render(state: CurrenciesViewState) {
        state.data?.initialState?.let {
            infoDataSubject.onNext(it)
        }
        when {
            state.isLoading -> {

            }
            state.error != null -> {

            }
            state.data != null -> {
                ifViewAttached { it.render(state.data.getList()) }
            }
        }
    }

}