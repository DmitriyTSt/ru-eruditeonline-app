package ru.eruditeonline.app.presentation.ui.competition.items

import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class CompetitionItemsDestinations @Inject constructor() {
    /** Фильтр */
    fun filter(filter: CompetitionFilters) = Destination.Action(
        CompetitionItemsFragmentDirections.actionCompetitionItemsFragmentToCompetitionFilterFragment(filter)
    )
}
