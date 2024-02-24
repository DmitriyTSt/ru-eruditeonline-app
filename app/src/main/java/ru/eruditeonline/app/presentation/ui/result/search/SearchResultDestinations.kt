package ru.eruditeonline.app.presentation.ui.result.search

import ru.eruditeonline.app.presentation.navigation.Action
import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams
import ru.eruditeonline.architecture.presentation.navigation.Destination
import ru.eruditeonline.architecture.presentation.navigation.Destinations
import javax.inject.Inject

class SearchResultDestinations @Inject constructor() : Destinations {
    /** Список результов */
    fun resultList(text: String) = Destination.Action(
        SearchResultFragmentDirections.actionSearchResultFragmentToUserResultListFragment(
            UserResultParams.Email(text)
        )
    )
}
