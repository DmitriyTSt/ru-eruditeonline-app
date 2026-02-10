package ru.eruditeonline.app.presentation.composeui.dashboard

import ru.eruditeonline.app.presentation.composeui.competition.detail.Competition
import ru.eruditeonline.app.presentation.composeui.debug.Debug
import ru.eruditeonline.app.presentation.composeui.result.info.Info
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardDestinations
import javax.inject.Inject

class ComposeDashboardDestinationsImpl @Inject constructor() : DashboardDestinations {
    override fun competitionItem(id: Int): Destination {
        return Destination.ComposeScreen(Competition(id))
    }

    override fun webPage(path: String): Destination {
        return Destination.ComposeScreen(Info(path))
    }

    override fun debug(): Destination {
        return Destination.ComposeScreen(Debug)
    }
}
