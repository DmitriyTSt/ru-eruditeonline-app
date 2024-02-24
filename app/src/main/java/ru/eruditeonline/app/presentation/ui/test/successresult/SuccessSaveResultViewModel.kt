package ru.eruditeonline.app.presentation.ui.test.successresult

import ru.eruditeonline.architecture.presentation.base.BaseViewModel
import javax.inject.Inject

class SuccessSaveResultViewModel @Inject constructor(
    private val destinations: SuccessSaveResultDestinations,
) : BaseViewModel() {

    fun openResult(id: Int) {
        navigate(destinations.result(id))
    }

    fun openCompetitions() {
        navigate(destinations.competitions())
    }
}
