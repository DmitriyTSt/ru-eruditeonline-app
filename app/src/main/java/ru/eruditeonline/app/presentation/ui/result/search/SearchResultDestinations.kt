package ru.eruditeonline.app.presentation.ui.result.search

import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams
import javax.inject.Inject

class SearchResultDestinations @Inject constructor() {
    /** Список результов */
    fun resultList(text: String) = Destination.Action(
        SearchResultFragmentDirections.actionSearchResultFragmentToUserResultListFragment(
            UserResultParams.Email(text)
        )
    )
}
