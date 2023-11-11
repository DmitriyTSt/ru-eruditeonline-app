package ru.eruditeonline.app.presentation.navigation

import android.content.Intent
import android.os.Parcelable
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

sealed class Destination {
    class Activity(val intent: Intent) : Destination()
    class Action(val direction: NavDirections, val navOptions: NavOptions? = null) : Destination()
    class DeepLink(val navDeepLinkRequest: NavDeepLinkRequest, val navOptions: NavOptions? = null) : Destination()
    class Stack(vararg val destinations: Destination) : Destination()
    data object Back : Destination()
    class ComposeScreenWithArg<T : Parcelable>(val route: String, val arg: T, val key: String) : Destination()
    class ComposeScreen(val route: String) : Destination()
}
