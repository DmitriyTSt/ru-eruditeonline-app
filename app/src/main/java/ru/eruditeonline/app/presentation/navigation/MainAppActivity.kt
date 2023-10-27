package ru.eruditeonline.app.presentation.navigation

import android.content.Context
import android.content.Intent
import ru.eruditeonline.app.presentation.composeui.mainacitivty.MainComposeActivity
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity

private const val COMPOSE_ENABLED = true

object MainAppActivity {
    fun createStartIntent(context: Context, from401Error: Boolean = false): Intent {
        return if (COMPOSE_ENABLED) {
            MainComposeActivity.createStartIntent(context, from401Error)
        } else {
            MainActivity.createStartIntent(context, from401Error)
        }
    }
}