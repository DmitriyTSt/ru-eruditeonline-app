package ru.eruditeonline.app.presentation.composeui.base

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel

@Composable
fun ObserveDestinations(navController: NavController, viewModel: BaseViewModel) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.destinationChannel.collectLatest { destination ->
            observeDestination(context, navController, destination)
        }
    }
}

private fun observeDestination(context: Context, navController: NavController, destination: Destination) {
    when (destination) {
        is Destination.Action -> error("Destination.Action not supported in compose screens")
        is Destination.Activity -> context.startActivity(destination.intent)
        is Destination.Back -> navController.popBackStack()
        is Destination.DeepLink -> error("Destination.DeepLink not supported in compose screens")
        is Destination.Stack -> destination.destinations.forEach { innerDestination ->
            observeDestination(context, navController, innerDestination)
        }
        is Destination.ComposeScreen -> navController.navigate(destination.route)
    }
}