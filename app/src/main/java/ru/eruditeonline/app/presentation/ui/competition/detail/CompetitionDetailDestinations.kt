package ru.eruditeonline.app.presentation.ui.competition.detail

import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class CompetitionDetailDestinations @Inject constructor() : Destinations {

    /** Прохождение теста */
    fun testPassage(id: String) = Destination.Action(
        CompetitionDetailFragmentDirections.actionCompetitionDetailFragmentToTestPassageFragment(id)
    )
}
