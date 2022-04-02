package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    preferencesStorage: PreferencesStorage,
) : TokenRepository {
    override var accessToken: String? =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjQ5MDMwODcxfQ.N3pmHjHhJ6rFmOmWgNnBj-" +
            "pp0zJIY89CtrEGMmkxB6GLkYIGmWBVOf13i4FNt83cIgjmeIWA1T4LANHaxxxYLw" // preferencesStorage.accessToken
    override var refreshToken = preferencesStorage.refreshToken
}
