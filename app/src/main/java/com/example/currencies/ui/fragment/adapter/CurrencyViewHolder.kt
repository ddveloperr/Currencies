package com.example.currencies.ui.fragment.adapter

import android.graphics.drawable.Drawable
import android.text.Editable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.common.ext.toBigDecimal
import com.example.common.model.StringSource
import com.example.common.recycler.OnItemClickListener
import com.example.common.text.AbstractTextWatcher
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.*
import java.math.BigDecimal

typealias OnRateChanged = (item: CurrencyViewHolderItem, value: BigDecimal?) -> Unit

class CurrencyViewHolder(
    override val containerView: View,
    private val onItemClickListener: OnItemClickListener<CurrencyViewHolderItem>,
    private val onRateChanged: OnRateChanged
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var item: CurrencyViewHolderItem? = null

    init {
        containerView.setOnClickListener {
            item?.let { item ->
                onItemClickListener.invoke(item)
            }

        }
    }

    private val textWatcher = object : AbstractTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            item?.let {
                onRateChanged.invoke(it, s.toBigDecimal())
            }
        }
    }

    fun bindItem(item: CurrencyViewHolderItem) {
        this.item = item
        bindIcon(item)
        bindSubtitle(item)
        bindEditText(item)

        currencyTitle.text = item.title.getText(containerView.context)
    }

    private fun bindIcon(item: CurrencyViewHolderItem) {
        flagIcon.setImageDrawable(getCurrencyDrawable(item))
    }

    private fun bindSubtitle(item: CurrencyViewHolderItem) {
        currencySubtitle.isVisible = item.subtitle != null
        item.subtitle?.let {
            currencySubtitle.text = it.getText(containerView.context)
        }
    }

    private fun bindEditText(item: CurrencyViewHolderItem) {
        rateValue.removeTextChangedListener(textWatcher)
        rateValue.text = Editable.Factory.getInstance()
            .newEditable(getEditableValue(item).getText(containerView.context))
        rateValue.addTextChangedListener(textWatcher)
    }

    private fun getEditableValue(item: CurrencyViewHolderItem): StringSource {
        val multiplicator = item.multiplicator
        return if (multiplicator != null) StringSource.Text((item.rate * item.multiplicator).toString()) else StringSource.Empty
    }

    private fun getCurrencyDrawable(item: CurrencyViewHolderItem): Drawable? {
        return if (item.icon != null) {
            ContextCompat.getDrawable(containerView.context, item.icon)
        } else {
            CurrenciesViewHolderDrawableHelper.getDefaultDrawable(
                containerView.context,
                item.currency.name[0].toString()
            )
        }
    }
}