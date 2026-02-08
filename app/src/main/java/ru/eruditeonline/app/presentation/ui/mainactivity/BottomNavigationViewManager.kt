package ru.eruditeonline.app.presentation.ui.mainactivity

import android.view.View
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.core.view.marginTop

interface BottomNavigationViewManager {
    fun setNavigationViewVisibility(isVisible: Boolean)
    fun getNavigationView(): View

    /** Нижний отступ контента над нижнем меню */
    fun getMenuMarginBottom(): Int {
        return if (getNavigationView().isVisible) {
            getNavigationView().run { height + marginTop + marginBottom }
        } else {
            0
        }
    }
}
