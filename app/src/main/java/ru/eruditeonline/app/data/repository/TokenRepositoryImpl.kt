package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) : TokenRepository {
    override var accessToken
        get() = preferencesStorage.accessToken
        set(value) {
            preferencesStorage.accessToken = value
        }
    override var refreshToken
        get() = preferencesStorage.refreshToken
        set(value) {
            preferencesStorage.refreshToken = value
        }
}
