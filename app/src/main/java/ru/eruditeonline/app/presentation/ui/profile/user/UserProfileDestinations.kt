package ru.eruditeonline.app.presentation.ui.profile.user

import ru.eruditeonline.app.presentation.navigation.Destination
import ru.eruditeonline.app.presentation.ui.profile.ProfileFragmentDirections
import ru.eruditeonline.app.presentation.ui.result.search.SearchResultMode
import ru.eruditeonline.app.presentation.ui.result.user.UserResultParams
import javax.inject.Inject

class UserProfileDestinations @Inject constructor() {
    /** Поиск результатов по названию конкурса или учатснику */
    fun searchResultsByQuery() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSearchResultFragment(SearchResultMode.QUERY)
    )

    /** Результаты пользователя */
    fun userResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToUserResultListFragment(UserResultParams.All)
    )

    /** Итоги */
    fun commonResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToCommonResultListFragment()
    )
}