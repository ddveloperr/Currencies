package com.example.currencies.ui.fragment.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.text.TextPaint
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.currencies.R


object CurrenciesViewHolderDrawableHelper {
    fun getDefaultDrawable(context: Context, letter: String): BitmapDrawable {
        val bm = ContextCompat.getDrawable(context, R.drawable.ic_currency_default)!!
            .toBitmap()
        val paint = TextPaint()
        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
        paint.textSize = 34f
        paint.textAlign = Paint.Align.CENTER

        val canvas = Canvas(bm)
        val xPos: Int = canvas.width / 2
        val yPos = (canvas.height / 2 - (paint.descent() + paint.ascent()) / 2)
        canvas.drawText(
            letter,
            xPos.toFloat(),
            yPos.toFloat(),
            paint
        )

        return BitmapDrawable(context.resources, bm)
    }
}