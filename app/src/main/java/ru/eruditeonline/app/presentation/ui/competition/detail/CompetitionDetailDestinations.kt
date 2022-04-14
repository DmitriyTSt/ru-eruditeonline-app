package ru.eruditeonline.app.presentation.ui.competition.detail

import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class CompetitionDetailDestinations @Inject constructor() {

    /** Прохождение теста */
    fun testPassage(id: String) = Destination.Action(
        CompetitionDetailFragmentDirections.actionCompetitionDetailFragmentToTestPassageFragment(id)
    )
}
