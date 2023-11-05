package ru.eruditeonline.app.presentation.navigation

import android.content.Context
import android.content.Intent
import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.app.presentation.composeui.mainacitivty.MainComposeActivity
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity
import javax.inject.Inject

class AppStarter @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) {
    fun createStartIntent(context: Context, from401Error: Boolean = false): Intent {
        return if (preferencesStorage.isComposeEnabled) {
            MainComposeActivity.createStartIntent(context, from401Error)
        } else {
            MainActivity.createStartIntent(context, from401Error)
        }
    }
}