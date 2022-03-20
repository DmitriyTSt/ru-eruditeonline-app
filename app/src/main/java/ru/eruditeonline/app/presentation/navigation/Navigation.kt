package ru.eruditeonline.app.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import timber.log.Timber

fun Fragment.observeNavigationCommands(viewModel: BaseViewModel) {
    viewModel.destinationLiveEvent.observe(viewLifecycleOwner) { destination ->
        processDestination(destination)
    }
}

private fun Fragment.processDestination(destination: Destination) {
    when (destination) {
        is Destination.Action -> findNavController().navigateSafe(
            destination.direction,
            destination.navOptions,
        )
        is Destination.Back -> findNavController().popBackStack()
        is Destination.DeepLink -> findNavController().navigateSafe(
            destination.navDeepLinkRequest,
            destination.navOptions,
        )
        is Destination.Activity -> startActivity(destination.intent)
        is Destination.Stack -> {
            destination.destinations.forEach { processDestination(it) }
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
