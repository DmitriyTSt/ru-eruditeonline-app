package ru.eruditeonline.app.data

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.app.domain.AuthorizationManager
import javax.inject.Inject

class AuthorizationManagerImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) : AuthorizationManager {

    override val isAuthorized: Boolean
        get() = preferencesStorage.isSignedIn
}