package ru.eruditeonline.app.presentation.ui.result.search

import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams
import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class SearchResultDestinations @Inject constructor() : Destinations {
    /** Список результов */
    fun resultList(text: String) = Destination.Action(
        SearchResultFragmentDirections.actionSearchResultFragmentToUserResultListFragment(
            UserResultParams.Email(text)
        )
    )
}
