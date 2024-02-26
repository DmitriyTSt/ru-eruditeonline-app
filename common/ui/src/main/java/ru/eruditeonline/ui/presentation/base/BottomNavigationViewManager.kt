package ru.eruditeonline.ui.presentation.base

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
            getNavigationView().let { navigationView ->
                navigationView.height + navigationView.marginTop + navigationView.marginBottom
            }
        } else {
            0
        }
    }
}
