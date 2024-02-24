package ru.eruditeonline.app.presentation.navigation

import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import ru.eruditeonline.architecture.presentation.navigation.AbstractScreenDestination
import ru.eruditeonline.architecture.presentation.navigation.Destination

class DeepLinkDestination(
    val navDeepLinkRequest: NavDeepLinkRequest,
    val navOptions: NavOptions? = null,
) : AbstractScreenDestination

@Suppress("FunctionName")
fun Destination.Companion.DeepLink(navDeepLinkRequest: NavDeepLinkRequest, navOptions: NavOptions? = null): Destination {
    return Destination.Screen(DeepLinkDestination(navDeepLinkRequest, navOptions))
}
