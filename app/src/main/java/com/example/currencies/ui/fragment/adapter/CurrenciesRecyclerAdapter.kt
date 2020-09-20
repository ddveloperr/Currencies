package com.example.currencies.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.recycler.OnItemClickListener
import com.example.currencies.R

class CurrenciesRecyclerAdapter(
    private val onItemClickListener: OnItemClickListener<CurrencyViewHolderItem>,
    private val onRateChanged: OnRateChanged
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val adapterItems: MutableList<CurrencyViewHolderItem> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false),
            onItemClickListener,
            onRateChanged
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    override fun getItemCount(): Int = adapterItems.size

    fun clearAndAddAll(items: List<CurrencyViewHolderItem>) {
        val diffCallback = CurrenciesDiffCallback(adapterItems, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        adapterItems.clear()
        adapterItems.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).currency.hashCode().toLong()
    }

    private fun getItem(position: Int): CurrencyViewHolderItem = adapterItems[position]
}