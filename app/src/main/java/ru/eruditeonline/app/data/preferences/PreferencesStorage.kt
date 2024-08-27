package ru.eruditeonline.app.data.preferences

import ru.eruditeonline.app.data.preferences.base.RegularPreferenceStorage
import ru.eruditeonline.app.data.preferences.base.SecuredPreferenceStorage
import ru.eruditeonline.app.data.preferences.base.boolean
import ru.eruditeonline.app.data.preferences.base.string
import ru.eruditeonline.app.presentation.managers.Theme
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_ACCESS_TOKEN = "profile_access_token"
private const val KEY_REFRESH_TOKEN = "profile_refresh_token"
private const val KEY_IS_SIGNED_IN = "is_signed_in"
private const val KEY_CURRENT_THEME = "current_theme"

@Singleton
class PreferencesStorage @Inject constructor(
    regularPreferenceStorage: RegularPreferenceStorage,
    securedPreferenceStorage: SecuredPreferenceStorage,
) {

    /** Аксес токен */
    var accessToken: String? by securedPreferenceStorage.string(KEY_ACCESS_TOKEN, sync = true)

    /** Рефреш токен */
    var refreshToken: String? by securedPreferenceStorage.string(KEY_REFRESH_TOKEN, sync = true)

    /** Авторизован ли пользователь */
    var isSignedIn: Boolean by regularPreferenceStorage.boolean(KEY_IS_SIGNED_IN, sync = true)

    /** Текущая тема */
    var currentTheme: String? by regularPreferenceStorage.string(KEY_CURRENT_THEME, Theme.LIGHT.toString())
}
