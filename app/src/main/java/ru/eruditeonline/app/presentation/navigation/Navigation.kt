package ru.eruditeonline.app.presentation.navigation

import android.content.Intent
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import timber.log.Timber

fun Fragment.observeNavigationCommands(viewModel: BaseViewModel) {
    viewModel.destinationLiveEvent.observe(viewLifecycleOwner) { destination ->
        processDestination(
            findNavController(),
            ::startActivity,
            destination,
        )
    }
}

fun AppCompatActivity.observeNavigationCommands(viewModel: BaseViewModel, @IdRes containerId: Int) {
    viewModel.destinationLiveEvent.observe(this) { destination ->
        processDestination(
            findNavController(containerId),
            ::startActivity,
            destination,
        )
    }
}

private fun processDestination(
    navController: NavController,
    startActivity: (Intent) -> Unit,
    destination: Destination,
) {
    when (destination) {
        is Destination.Action -> navController.navigateSafe(
            destination.direction,
            destination.navOptions,
        )
        is Destination.Back -> navController.popBackStack()
        is Destination.DeepLink -> navController.navigateSafe(
            destination.navDeepLinkRequest,
            destination.navOptions,
        )
        is Destination.Activity -> startActivity(destination.intent)
        is Destination.Stack -> {
            destination.destinations.forEach { processDestination(navController, startActivity, it) }
        }
    }
}

private fun NavController.navigateSafe(direction: NavDirections, navOptions: NavOptions? = null) {
    currentDestination?.getAction(direction.actionId)?.let { navigate(direction, navOptions) }
}

// В блоке try/catch защита от неправильных диплинков
private fun NavController.navigateSafe(navDeepLinkRequest: NavDeepLinkRequest, navOptions: NavOptions? = null) {
    try {
        currentDestination?.let { navigate(navDeepLinkRequest, navOptions) }
    } catch (ex: IllegalStateException) {
        Timber.e(ex)
    }
}
