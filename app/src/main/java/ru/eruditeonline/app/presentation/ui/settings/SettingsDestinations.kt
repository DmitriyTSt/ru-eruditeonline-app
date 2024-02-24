package ru.eruditeonline.app.presentation.ui.settings

import android.content.Context
import android.content.Intent
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity
import ru.eruditeonline.architecture.presentation.navigation.Destination
import ru.eruditeonline.architecture.presentation.navigation.Destinations
import javax.inject.Inject

class SettingsDestinations @Inject constructor(
    private val context: Context,
) : Destinations {
    /** Перезапуск приложения */
    fun restartApp() = Destination.Activity(
        MainActivity.createStartIntent(context).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    )
}
