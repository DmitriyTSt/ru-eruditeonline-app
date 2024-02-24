package ru.eruditeonline.app.presentation.ui.competition.detail

import ru.eruditeonline.app.presentation.navigation.Action
import ru.eruditeonline.architecture.presentation.navigation.Destination
import ru.eruditeonline.architecture.presentation.navigation.Destinations
import javax.inject.Inject

class CompetitionDetailDestinations @Inject constructor() : Destinations {

    /** Прохождение теста */
    fun testPassage(id: String) = Destination.Action(
        CompetitionDetailFragmentDirections.actionCompetitionDetailFragmentToTestPassageFragment(id)
    )
}
