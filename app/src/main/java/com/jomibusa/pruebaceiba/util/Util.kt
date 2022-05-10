package com.jomibusa.pruebaceiba.util

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

object Util {

    fun createSpannableString(
        context: Context,
        text: Int,
        color: Int,
        isBold: Boolean = false
    ): SpannableString {
        val spannableString = SpannableString(context.getString(text))
        spannableString.setSpan(
            ForegroundColorSpan(context.getColor(color)),
            0,
            spannableString.length,
            0
        )
        if (isBold) {
            spannableString.setSpan(StyleSpan(Typeface.BOLD), 0, spannableString.length, 0)
        }
        return spannableString
    }

}