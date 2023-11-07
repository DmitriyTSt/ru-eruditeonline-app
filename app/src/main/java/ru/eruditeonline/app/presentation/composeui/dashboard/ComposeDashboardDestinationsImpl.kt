package ru.eruditeonline.app.presentation.composeui.dashboard

import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardDestinations
import javax.inject.Inject

class ComposeDashboardDestinationsImpl @Inject constructor() : DashboardDestinations {
    override fun competitionItem(id: Int): Destination {
        return Destination.ComposeScreen(Screen.Competition.route(id))
    }

    override fun webPage(path: String): Destination {
        return Destination.ComposeScreen(Screen.Debug.route)
    }

    override fun debug(): Destination {
        return Destination.ComposeScreen(Screen.Debug.route)
    }
}
