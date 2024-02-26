package ru.eruditeonline.app.presentation.managers

import androidx.annotation.StyleRes
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.ui.presentation.theme.ThemeManager
import javax.inject.Inject

class ThemeManagerImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) : ThemeManager {

    var theme: Theme = initTheme()
        set(value) {
            field = value
            preferencesStorage.currentTheme = value.toString()
        }

    @StyleRes
    override fun getThemeResource(): Int {
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
