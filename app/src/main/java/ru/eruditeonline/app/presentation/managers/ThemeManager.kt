package ru.eruditeonline.app.presentation.managers

import androidx.annotation.StyleRes
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.preferences.PreferencesStorage
import javax.inject.Inject

class ThemeManager @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) {
    var theme: Theme = initTheme()
        set(value) {
            field = value
            preferencesStorage.currentTheme = value.toString()
        }

    @StyleRes
    fun getThemeResource(): Int {
        return when (theme) {
            Theme.COLORED -> R.style.Theme_EruditeOnline
            Theme.LIGHT -> R.style.Theme_EruditeOnline_Light
        }
    }

    private fun initTheme(): Theme {
        return preferencesStorage.currentTheme.let {
            try {
                Theme.valueOf(it)
            } catch (e: Exception) {
                Theme.COLORED
            }
        }
    }
}

enum class Theme {
    COLORED,
    LIGHT,
}
