package ru.eruditeonline.app.presentation.ui.result.user

import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class UserResultListDestinations @Inject constructor() {
    /** Результат */
    fun result(id: Int) = Destination.Action(
        UserResultListFragmentDirections.actionUserResultListFragmentToResultDetailGraph(id)
    )
}