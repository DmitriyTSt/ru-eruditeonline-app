@file:Suppress("FunctionName")

package ru.eruditeonline.navigation

import android.content.Intent
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

fun Destination.Companion.Action(direction: NavDirections, navOptions: NavOptions? = null): Destination {
    return Destination.Screen(NavigationUiScreenDestination.Action(direction, navOptions))
}

fun Destination.Companion.DeepLink(navDeepLinkRequest: NavDeepLinkRequest, navOptions: NavOptions? = null): Destination {
    return Destination.Screen(NavigationUiScreenDestination.DeepLink(navDeepLinkRequest, navOptions))
}

fun Destination.Companion.Activity(intent: Intent) = Destination.Screen(NavigationUiScreenDestination.Activity(intent))