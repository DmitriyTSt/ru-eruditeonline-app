package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    preferencesStorage: PreferencesStorage,
) : TokenRepository {
    override var accessToken: String? =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjQ4ODUxNTcyfQ.quFINcs96Y4DADlxj9FJ2IAsh_" +
            "AWnkeceZIWsOSaiXRYkO5DTctLnBivM9pHKQQKN4oYiFDKmNp9CHUJ5Qsrfg" //preferencesStorage.accessToken
    override var refreshToken = preferencesStorage.refreshToken
}
