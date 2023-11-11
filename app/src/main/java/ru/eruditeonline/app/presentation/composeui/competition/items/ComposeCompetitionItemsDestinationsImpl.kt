package ru.eruditeonline.app.presentation.composeui.competition.items

import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.presentation.composeui.model.Screen
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsDestinations
import javax.inject.Inject

class ComposeCompetitionItemsDestinationsImpl @Inject constructor() : CompetitionItemsDestinations {
    override fun filter(filter: CompetitionFilters): Destination {
        return Destination.ComposeScreenWithArg(Screen.CompetitionFilter.route, filter, CompetitionFilters::class.java.name)
    }

    override fun competitionItem(id: Int): Destination {
        return Destination.ComposeScreen(Screen.Competition.route(id))
    }
}
