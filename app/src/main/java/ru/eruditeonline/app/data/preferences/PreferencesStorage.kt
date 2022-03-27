package ru.eruditeonline.app.data.preferences

import android.annotation.SuppressLint
import ru.eruditeonline.app.data.preferences.base.RegularPreferenceStorage
import ru.eruditeonline.app.data.preferences.base.SecuredPreferenceStorage
import ru.eruditeonline.app.data.preferences.base.UserIndependentPreferenceStorage
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_ACCESS_TOKEN = "profile_access_token"
private const val KEY_REFRESH_TOKEN = "profile_refresh_token"

@Singleton
class PreferencesStorage @Inject constructor(
    private val regularPreferenceStorage: RegularPreferenceStorage,
    private val securedPreferenceStorage: SecuredPreferenceStorage,
    private val userIndependentPreferenceStorage: UserIndependentPreferenceStorage,
) {

    var accessToken: String?
        get() = securedPreferenceStorage.getString(KEY_ACCESS_TOKEN, null)
        @SuppressLint("ApplySharedPref")
        internal set(accessToken) {
            // коммит тут стоит осознанно, для случаев с 401
            securedPreferenceStorage.edit().putString(KEY_ACCESS_TOKEN, accessToken).commit()
        }

    var refreshToken: String?
        get() = securedPreferenceStorage.getString(KEY_REFRESH_TOKEN, null)
        @SuppressLint("ApplySharedPref")
        internal set(refreshToken) {
            // коммит тут стоит осознанно, для случаев с 401
            securedPreferenceStorage.edit().putString(KEY_REFRESH_TOKEN, refreshToken).commit()
        }
}