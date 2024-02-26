package ru.eruditeonline.app.presentation.ui.result.user

import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class UserResultListDestinations @Inject constructor() : Destinations {
    /** Результат */
    fun result(id: Int) = Destination.Action(
        UserResultListFragmentDirections.actionUserResultListFragmentToResultDetailGraph(id)
    )
}
