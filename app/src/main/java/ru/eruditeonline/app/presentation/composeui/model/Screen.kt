package ru.eruditeonline.app.presentation.composeui.model

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    data object Dashboard : Screen(route = "dashboard")
    data object Competitions : Screen(route = "competitions")
    data object Rating : Screen(route = "rating")
    data object Profile : Screen(route = "profile")

    data object Competition : Screen(
        route = "competition/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType }),
    ) {
        override fun route(vararg args: Any): String {
            return "competition/%d".format(args.first() as Int)
        }
    }

    data object Settings : Screen(route = "settings")

    open fun route(vararg args: Any): String {
        return this.route
    }
}