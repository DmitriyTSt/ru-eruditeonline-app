package ru.eruditeonline.navigation

import android.content.Intent
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import timber.log.Timber

fun Fragment.observeNavigationCommands(navigationController: NavigationController) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigationController.destinationFlow.collect { destination ->
                if (destination != Destination.Idle) {
                    navigationController.navigated()
                }
                processDestination(
                    findNavController(),
                    ::startActivity,
                    destination,
                )
            }
        }
    }
}

fun AppCompatActivity.observeNavigationCommands(navigationController: NavigationController, @IdRes containerId: Int) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            navigationController.destinationFlow.collect { destination ->
                if (destination != Destination.Idle) {
                    navigationController.navigated()
                }
                processDestination(
                    findNavController(containerId),
                    ::startActivity,
                    destination,
                )
            }
        }
    }
}

private fun processDestination(
    navController: NavController,
    startActivity: (Intent) -> Unit,
    destination: Destination,
) {
    when (destination) {
        is Destination.Idle -> Unit
        is Destination.Screen -> {
            when (val screenDestination = destination.screen as NavigationUiScreenDestination) {
                is NavigationUiScreenDestination.Action -> navController.navigateSafe(
                    screenDestination.direction,
                    screenDestination.navOptions,
                )
                is NavigationUiScreenDestination.DeepLink -> navController.navigateSafe(
                    screenDestination.navDeepLinkRequest,
                    screenDestination.navOptions,
                )
                is NavigationUiScreenDestination.Activity -> startActivity(screenDestination.intent)
            }
        }
        is Destination.Back -> navController.popBackStack()
        is Destination.Stack -> {
            destination.destinations.forEach { processDestination(navController, startActivity, it) }
        }
    }
}

private fun NavController.navigateSafe(direction: NavDirections, navOptions: NavOptions? = null) {
    currentDestination?.getAction(direction.actionId)?.let { navigate(direction, navOptions) } ?: run {
        Timber.e("NavController.navigateSafe actionId ${direction.actionId} not found")
    }
}

// В блоке try/catch защита от неправильных диплинков
private fun NavController.navigateSafe(navDeepLinkRequest: NavDeepLinkRequest, navOptions: NavOptions? = null) {
    try {
        currentDestination?.let { navigate(navDeepLinkRequest, navOptions) }
    } catch (ex: IllegalStateException) {
        Timber.e("NavController.navigateSafe deepLink ${navDeepLinkRequest.uri} not found")
    }
}
