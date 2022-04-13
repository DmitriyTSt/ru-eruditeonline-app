package ru.eruditeonline.app.presentation.ui.test.passage

import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class TestPassageDestinations @Inject constructor() {
    /** Проверка теста */
    fun checkResult(data: CompetitionPassData) = Destination.Action(
        TestPassageFragmentDirections.actionTestPassageFragmentToTestTempResultFragment(data)
    )
}
