package ru.eruditeonline.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationControllerImpl : NavigationController {

    private val _destinationFlow = MutableSharedFlow<Destination>(extraBufferCapacity = 1)
    override val destinationFlow: SharedFlow<Destination> = _destinationFlow.asSharedFlow()

    override fun navigate(destination: Destination) {
        _destinationFlow.tryEmit(destination)
    }

    override fun navigateBack() {
        _destinationFlow.tryEmit(Destination.Back)
    }
}