package ru.eruditeonline.app.presentation.ui.views

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

typealias OnWebViewScrollChangeListener = ((v: WebView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) -> Unit)

/**
 * WebView со слушателем скрола
 */
class ScrollableWebView : WebView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var onWebViewScrollChangeListener: OnWebViewScrollChangeListener? = null
        private set

    fun setOnWebViewScrollChangeListener(listener: OnWebViewScrollChangeListener?) {
        onWebViewScrollChangeListener = listener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        onWebViewScrollChangeListener?.invoke(this, l, t, oldl, oldt)
    }
}
