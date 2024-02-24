package ru.eruditeonline.app.presentation.navigation

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import ru.eruditeonline.architecture.presentation.navigation.AbstractScreenDestination
import ru.eruditeonline.architecture.presentation.navigation.Destination

class ActionDestination(
    val direction: NavDirections,
    val navOptions: NavOptions? = null,
) : AbstractScreenDestination

@Suppress("FunctionName")
fun Destination.Companion.Action(direction: NavDirections, navOptions: NavOptions? = null): Destination {
    return Destination.Screen(ActionDestination(direction, navOptions))
}
