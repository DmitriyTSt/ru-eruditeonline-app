package ru.eruditeonline.app.presentation.composeui.competition.items

import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.presentation.composeui.competition.detail.Competition
import ru.eruditeonline.app.presentation.composeui.competition.filter.CompetitionFilter
import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.competition.items.CompetitionItemsDestinations
import javax.inject.Inject

class ComposeCompetitionItemsDestinationsImpl @Inject constructor() : CompetitionItemsDestinations {
    override fun filter(filter: CompetitionFilters): Destination {
        return Destination.ComposeScreen(CompetitionFilter(filter))
    }

    override fun competitionItem(id: Int): Destination {
        return Destination.ComposeScreen(Competition(id))
    }
}
