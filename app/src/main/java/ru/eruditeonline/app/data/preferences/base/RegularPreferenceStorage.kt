package ru.eruditeonline.app.data.preferences.base

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_FILE_NAME = "regular_preferences_storage"

/**
 * Обычный файл префсов
 */
@Singleton
class RegularPreferenceStorage @Inject constructor(
    context: Context,
) : BasePreferencesStorage(context) {

    override fun createPreferences(): SharedPreferences {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }
}