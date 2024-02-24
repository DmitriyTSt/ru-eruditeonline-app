package ru.eruditeonline.app.presentation.ui.splash

import android.content.Context
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.AppUpdate
import ru.eruditeonline.app.presentation.navigation.Action
import ru.eruditeonline.app.presentation.navigation.DeepLink
import ru.eruditeonline.architecture.presentation.navigation.Destination
import ru.eruditeonline.architecture.presentation.navigation.Destinations
import javax.inject.Inject

class SplashDestinations @Inject constructor(
    private val context: Context,
) : Destinations {
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

    /** Обновление приложения */
    fun appUpdate(appUpdate: AppUpdate) = Destination.Action(
        SplashFragmentDirections.actionSplashFragmentToAppUpdateFragment(appUpdate)
    )
}
