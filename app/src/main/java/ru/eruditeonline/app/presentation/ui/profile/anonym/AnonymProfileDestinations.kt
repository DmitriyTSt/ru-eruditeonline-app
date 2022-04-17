package ru.eruditeonline.app.presentation.ui.profile.anonym

import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.profile.ProfileFragmentDirections
import ru.eruditeonline.app.presentation.ui.result.search.SearchResultMode
import javax.inject.Inject

class AnonymProfileDestinations @Inject constructor() {
    /** Авторизация */
    fun login() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToAuthGraph()
    )

    /** Итоги */
    fun commonResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToCommonResultListFragment()
    )

    /** Поиск результатов по email */
    fun searchResultsByEmail() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSearchResultFragment(SearchResultMode.EMAIL)
    )
}