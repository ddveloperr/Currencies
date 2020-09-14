package com.example.currencies.ui.fragment

import com.example.currencies.domain.model.Currency
import com.example.currencies.ui.fragment.mvi.*
import com.example.mvi.MviPresenter
import com.example.mvi.MviSubscriptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class CurrenciesFragmentPresenter @Inject constructor(
    useCase: CurrenciesUseCase,
    reducer: CurrenciesReducer
) : MviPresenter<CurrenciesFragmentView, CurrenciesViewAction, CurrenciesViewState, CurrenciesInitialState, CurrenciesPartialState, MviSubscriptions>(
    reducer,
    useCase
) {

    override fun init(){
        super.init()
        viewActionSubject.onNext(CurrenciesViewAction.StartFirstLoad)
    }

    override val infoDataSubject: BehaviorSubject<CurrenciesInitialState> =
        BehaviorSubject.createDefault(
            CurrenciesInitialState(Currency.USD)
        )

    override fun getInitialViewState(): CurrenciesViewState {
        return CurrenciesViewState(isLoading = true)
    }

    override fun render(state: CurrenciesViewState) {
        when {
            state.isLoading -> {

            }
            state.error != null -> {

            }
            state.data != null -> {
                ifViewAttached { it.render(state.data.items) }
            }
        }
    }

}