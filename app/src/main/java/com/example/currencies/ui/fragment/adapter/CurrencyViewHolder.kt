package com.example.currencies.ui.fragment.adapter

import android.text.Editable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.*

class CurrencyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bindItem(item: CurrencyViewHolderItem) {
        bindIcon(item)
        bindSubtitle(item)
        currencyTitle.text = item.title.getText(containerView.context)
        rateValue.text = Editable.Factory.getInstance().newEditable(item.value.toString())
    }

    private fun bindIcon(item: CurrencyViewHolderItem) {
        flagIcon.isInvisible = item.icon == null
        item.icon?.let {
            flagIcon.setImageDrawable(ContextCompat.getDrawable(containerView.context, it))
        }
    }

    private fun bindSubtitle(item: CurrencyViewHolderItem) {
        currencySubtitle.isVisible = item.subtitle != null
        item.subtitle?.let {
            currencySubtitle.text = it.getText(containerView.context)
        }
    }
}