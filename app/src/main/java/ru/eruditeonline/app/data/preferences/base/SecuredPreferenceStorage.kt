package ru.eruditeonline.app.data.preferences.base

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_SECURE_FILE_NAME = "secured_preferences_storage"

/**
 * Шифрованные префсы (нет)
 */
@Singleton
class SecuredPreferenceStorage @Inject constructor(
    context: Context,
) : BasePreferencesStorage(context) {

    override fun createPreferences(): SharedPreferences {
        return context.getSharedPreferences(PREF_SECURE_FILE_NAME, Context.MODE_PRIVATE)
    }
}
