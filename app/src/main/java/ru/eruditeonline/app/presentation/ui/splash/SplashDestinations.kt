package ru.eruditeonline.app.presentation.ui.splash

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class SplashDestinations @Inject constructor(
    private val context: Context,
) {
    /** Главная */
    fun main() = Destination.Action(
        SplashFragmentDirections.actionSplashFragmentToDashboardGraph()
    )

    /** Дебаг */
    fun debug() = Destination.DeepLink(
        NavDeepLinkRequest.Builder
            .fromUri(context.getString(R.string.navigation_deep_link_to_debug).toUri())
            .build()
    )
}
