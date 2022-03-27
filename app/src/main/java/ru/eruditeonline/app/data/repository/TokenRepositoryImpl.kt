package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    preferencesStorage: PreferencesStorage,
) : TokenRepository {
    override var accessToken = preferencesStorage.accessToken
    override var refreshToken = preferencesStorage.refreshToken
}
