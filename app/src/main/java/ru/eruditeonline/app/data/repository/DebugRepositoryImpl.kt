package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.app.domain.repository.DebugRepository
import javax.inject.Inject

class DebugRepositoryImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) : DebugRepository {
    override suspend fun isComposeEnabled(): Boolean {
        return preferencesStorage.isComposeEnabled
    }

    override suspend fun setComposeEnabled(isEnabled: Boolean) {
        preferencesStorage.isComposeEnabled = isEnabled
    }
}
