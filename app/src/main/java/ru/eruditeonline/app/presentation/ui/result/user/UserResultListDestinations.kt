package ru.eruditeonline.app.presentation.ui.result.user

import ru.eruditeonline.app.presentation.navigation.Action
import ru.eruditeonline.architecture.presentation.navigation.Destination
import ru.eruditeonline.architecture.presentation.navigation.Destinations
import javax.inject.Inject

class UserResultListDestinations @Inject constructor() : Destinations {
    /** Результат */
    fun result(id: Int) = Destination.Action(
        UserResultListFragmentDirections.actionUserResultListFragmentToResultDetailGraph(id)
    )
}
