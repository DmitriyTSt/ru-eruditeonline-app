package ru.eruditeonline.app.presentation.navigation

import android.content.Intent
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen

sealed class Destination {
    class Activity(val intent: Intent) : Destination()
    class Action(val direction: NavDirections, val navOptions: NavOptions? = null) : Destination()
    class DeepLink(val navDeepLinkRequest: NavDeepLinkRequest, val navOptions: NavOptions? = null) : Destination()
    class Stack(vararg val destinations: Destination) : Destination()
    data object Back : Destination()
    class ComposeScreen(val screen: BaseScreen) : Destination()
}
