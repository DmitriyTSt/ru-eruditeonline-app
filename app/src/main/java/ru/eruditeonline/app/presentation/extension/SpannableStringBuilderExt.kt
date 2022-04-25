package ru.eruditeonline.app.presentation.extension

import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.text.inSpans

/**
 * Расширение для быстрого создания ссылок в Spannable String
 */
fun SpannableStringBuilder.link(action: () -> Unit, builderAction: SpannableStringBuilder.() -> Unit) {
    inSpans(
        object : ClickableSpan() {
            override fun onClick(widget: View) {
                action()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        },
        builderAction
    )
}
