package ru.eruditeonline.app.presentation.composeui.dashboard

import ru.eruditeonline.app.presentation.composeui.competition.detail.Competition
import ru.eruditeonline.app.presentation.composeui.debug.Debug
import ru.eruditeonline.app.presentation.composeui.webpage.WebPage
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.dashboard.DashboardDestinations
import javax.inject.Inject

class ComposeDashboardDestinationsImpl @Inject constructor() : DashboardDestinations {
    override fun competitionItem(id: Int): Destination {
        return Destination.ComposeScreen(Competition(id))
    }

    override fun webPage(path: String): Destination {
        return Destination.ComposeScreen(WebPage(path))
    }

    override fun debug(): Destination {
        return Destination.ComposeScreen(Debug)
    }
}
