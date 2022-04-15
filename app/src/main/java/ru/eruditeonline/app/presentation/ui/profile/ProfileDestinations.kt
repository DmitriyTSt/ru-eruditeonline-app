package ru.eruditeonline.app.presentation.ui.profile

import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.result.search.SearchResultMode
import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams
import javax.inject.Inject

class ProfileDestinations @Inject constructor() {
    /** Итоги */
    fun commonResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToCommonResultListFragment()
    )

    /** Поиск результатов по email */
    fun searchResultsByEmail() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSearchResultFragment(SearchResultMode.EMAIL)
    )

    /** Поиск результатов по названию конкурса или учатснику */
    fun searchResultsByQuery() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSearchResultFragment(SearchResultMode.QUERY)
    )

    /** Результаты пользователя */
    fun userResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToUserResultListFragment(UserResultParams.All)
    )

    /** Авторизация */
    fun login() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToAuthGraph()
    )
}
