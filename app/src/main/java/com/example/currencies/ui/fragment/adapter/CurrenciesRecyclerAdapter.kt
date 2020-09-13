package com.example.currencies.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencies.R

class CurrenciesRecyclerAdapter : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val adapterItems: MutableList<CurrencyViewHolderItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    override fun getItemCount(): Int = adapterItems.size

    fun clearAndAddAll(items: List<CurrencyViewHolderItem>) {
        adapterItems.clear()
        adapterItems.addAll(items)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): CurrencyViewHolderItem = adapterItems[position]
}