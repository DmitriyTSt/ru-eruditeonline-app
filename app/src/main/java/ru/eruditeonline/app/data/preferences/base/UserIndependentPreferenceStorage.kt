package ru.eruditeonline.app.data.preferences.base

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_USER_INDEPENDENT_FILE_NAME = "_user_independent_preferences_storage"

/**
 * Шаред преференсы, которые не должны очищаться при логауте
 */
@Singleton
class UserIndependentPreferenceStorage @Inject constructor(
    context: Context,
) : BasePreferencesStorage(context) {
    override fun createPreferences(): SharedPreferences {
        return context.getSharedPreferences(PREF_USER_INDEPENDENT_FILE_NAME, Context.MODE_PRIVATE)
    }
}
