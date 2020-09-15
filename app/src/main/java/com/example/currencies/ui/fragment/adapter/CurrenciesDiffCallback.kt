package com.example.currencies.ui.fragment.adapter

import androidx.recyclerview.widget.DiffUtil

class CurrenciesDiffCallback(
    private val oldList: List<CurrencyViewHolderItem>,
    private val newList: List<CurrencyViewHolderItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].currency === newList[newItemPosition].currency
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}