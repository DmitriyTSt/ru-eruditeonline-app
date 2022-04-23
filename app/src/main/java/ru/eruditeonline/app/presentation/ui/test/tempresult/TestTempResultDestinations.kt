package ru.eruditeonline.app.presentation.ui.test.tempresult

import ru.eruditeonline.app.data.model.test.CreatedResult
import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class TestTempResultDestinations @Inject constructor() {
    /** Выбор страны */
    fun selectCountry() = Destination.Action(
        TestTempResultFragmentDirections.actionTestTempResultFragmentToSelectCountryGraph()
    )

    /** Выбор диплома */
    fun selectDiploma() = Destination.Action(
        TestTempResultFragmentDirections.actionTestTempResultFragmentToSelectDiplomaFragment()
    )

    /** Успешное сохранение результата */
    fun successSaveResult(result: CreatedResult) = Destination.Action(
        TestTempResultFragmentDirections.actionTestTempResultFragmentToSuccessSaveResultFragment(result)
    )
}
