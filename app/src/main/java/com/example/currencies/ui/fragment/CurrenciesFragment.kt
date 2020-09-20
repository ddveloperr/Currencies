package com.example.currencies.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.currencies.R
import com.example.common.ext.lazyNone
import com.example.common.recycler.OnItemClickListener
import com.example.currencies.ui.fragment.adapter.CurrenciesRecyclerAdapter
import com.example.currencies.ui.fragment.adapter.CurrencyViewHolderItem
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_currencies.*
import javax.inject.Inject

class CurrenciesFragment : MvpFragment<CurrenciesFragmentView, CurrenciesFragmentPresenter>(),
    CurrenciesFragmentView, HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var currenciesPresenter: CurrenciesFragmentPresenter

    private val adapter by lazyNone {
        CurrenciesRecyclerAdapter(itemClickListener, onRateChanged = { item, value ->
            presenter.onEditValueChanged(item, value)
        })
    }

    private val itemClickListener: OnItemClickListener<CurrencyViewHolderItem> = { item ->
        presenter.onItemClicked(item)
        recyclerView.smoothScrollToPosition(0)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun createPresenter(): CurrenciesFragmentPresenter = currenciesPresenter

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
        initRecyclerView()
        presenter.init()
    }

    override fun render(items: List<CurrencyViewHolderItem>) {
        adapter.clearAndAddAll(items)
    }

    override fun showProgressBar() {
        progressBar.isVisible = true
    }

    override fun hideProgressBar() {
        progressBar.isVisible = false
    }

    private fun initRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.itemAnimator?.changeDuration = 0L
    }
}