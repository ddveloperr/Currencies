package com.example.currencies.ui.adapter

import android.text.Editable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.*

class CurrencyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bindItem(item: CurrencyViewHolderItem) {
        currencyTitle.text = item.title
        rateValue.text = Editable.Factory.getInstance().newEditable(item.value.toString())
    }
}