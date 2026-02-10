package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.app.domain.repository.ComposeThemeRepository
import javax.inject.Inject

class ComposeThemeRepositoryImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) : ComposeThemeRepository {
    override suspend fun getTheme(): String? {
        return preferencesStorage.composeCurrentTheme
    }

    override suspend fun setTheme(theme: String) {
        preferencesStorage.composeCurrentTheme = theme
    }
}
