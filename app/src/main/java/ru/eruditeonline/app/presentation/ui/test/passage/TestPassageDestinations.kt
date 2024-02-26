package ru.eruditeonline.app.presentation.ui.test.passage

import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class TestPassageDestinations @Inject constructor() : Destinations {
    /** Проверка теста */
    fun checkResult(data: CompetitionPassData) = Destination.Action(
        TestPassageFragmentDirections.actionTestPassageFragmentToTestTempResultFragment(data)
    )
}
