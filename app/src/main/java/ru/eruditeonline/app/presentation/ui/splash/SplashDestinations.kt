package ru.eruditeonline.app.presentation.ui.splash

import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class SplashDestinations @Inject constructor() {
    /** Главная */
    fun main() = Destination.Action(
        SplashFragmentDirections.actionSplashFragmentToDashboardGraph()
    )
}
