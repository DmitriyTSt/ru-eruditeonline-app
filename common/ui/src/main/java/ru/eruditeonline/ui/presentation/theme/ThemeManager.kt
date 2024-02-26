package ru.eruditeonline.ui.presentation.theme

import androidx.annotation.StyleRes

interface ThemeManager {

    @StyleRes
    fun getThemeResource(): Int
}