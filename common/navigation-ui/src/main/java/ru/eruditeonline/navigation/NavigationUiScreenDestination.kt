package ru.eruditeonline.navigation

import android.content.Intent
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

sealed class NavigationUiScreenDestination : AbstractScreenDestination {
    data class Action(
        val direction: NavDirections,
        val navOptions: NavOptions? = null,
    ) : NavigationUiScreenDestination()

    data class DeepLink(
        val navDeepLinkRequest: NavDeepLinkRequest,
        val navOptions: NavOptions? = null,
    ) : NavigationUiScreenDestination()

    data class Activity(
        val intent: Intent,
    ) : NavigationUiScreenDestination()
}