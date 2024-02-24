package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.network.domain.model.AccessToken
import ru.eruditeonline.network.domain.model.RefreshToken
import ru.eruditeonline.network.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) : TokenRepository {
    override var accessToken
        get() = preferencesStorage.accessToken?.takeIf { it.isNotEmpty() }?.let { AccessToken(it) }
        set(value) {
            preferencesStorage.accessToken = value?.value
        }
    override var refreshToken
        get() = preferencesStorage.refreshToken?.takeIf { it.isNotEmpty() }?.let { RefreshToken(it) }
        set(value) {
            preferencesStorage.refreshToken = value?.value
        }
}
