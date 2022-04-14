package ru.eruditeonline.app.presentation.ui.profile

import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class ProfileDestinations @Inject constructor() {
    /** Итоги */
    fun commonResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToCommonResultListFragment()
    )

    /** Поиск результатов */
    fun searchResults() = Destination.Action(
        ProfileFragmentDirections.actionProfileFragmentToSearchResultFragment()
    )
}
