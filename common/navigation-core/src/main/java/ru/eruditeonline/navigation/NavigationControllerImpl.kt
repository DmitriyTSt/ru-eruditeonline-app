package ru.eruditeonline.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NavigationControllerImpl(
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main.immediate),
) : NavigationController {

    private val _destinationFlow = MutableSharedFlow<Destination>()
    override val destinationFlow: SharedFlow<Destination> = _destinationFlow.asSharedFlow()

    override fun navigate(destination: Destination) {
        coroutineScope.launch { _destinationFlow.emit(destination) }
    }

    override fun navigateBack() {
        coroutineScope.launch { _destinationFlow.emit(Destination.Back) }
    }
}