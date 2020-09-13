package com.example.currencies.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.currencies.R
import com.example.currencies.common.utils.lazyNone
import com.example.currencies.ui.fragment.adapter.CurrenciesRecyclerAdapter
import com.example.currencies.ui.fragment.mvi.CurrenciesViewState
import com.hannesdorfmann.mosby3.mvi.MviFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_currencies.*
import javax.inject.Inject

class CurrenciesFragment : MviFragment<CurrenciesFragmentView, CurrenciesFragmentPresenter>(),
    CurrenciesFragmentView, HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var presenter: CurrenciesFragmentPresenter

    private val adapter by lazyNone { CurrenciesRecyclerAdapter() }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun createPresenter(): CurrenciesFragmentPresenter = presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currencies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        presenter.init()
    }

    override fun render(state: CurrenciesViewState) {
        when {
            state.isLoading -> {

            }
            state.error != null -> {

            }
            state.data != null -> {
                adapter.clearAndAddAll(state.data.items)
            }
        }
    }
}