package ru.eruditeonline.app.presentation.composeui.base

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.collectLatest
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel

val LocalBackStack = staticCompositionLocalOf<SnapshotStateList<BaseScreen>> { error("BackStack not found") }

@Composable
fun BaseViewModel.ObserveDestinations() {
    val context = LocalContext.current
    val backStack = LocalBackStack.current
    LaunchedEffect(Unit) {
        destinationChannel.collectLatest { destination ->
            observeDestination(context, backStack, destination)
        }
    }
}

private fun observeDestination(context: Context, backStack: SnapshotStateList<BaseScreen>, destination: Destination) {
    when (destination) {
        is Destination.Action -> error("Destination.Action not supported in compose screens")
        is Destination.Activity -> context.startActivity(destination.intent)
        is Destination.Back -> backStack.removeLastOrNull()
        is Destination.DeepLink -> error("Destination.DeepLink not supported in compose screens")
        is Destination.Stack -> destination.destinations.forEach { innerDestination ->
            observeDestination(context, backStack, innerDestination)
        }
        is Destination.ComposeScreen -> backStack.add(destination.screen)
    }
}