package ru.eruditeonline.app.presentation.ui.profile.user

import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    private val destinations: UserProfileDestinations,
) : BaseViewModel() {

    fun openCommonResults() {
        navigate(destinations.commonResults())
    }

    fun openSearchResultsByQuery() {
        navigate(destinations.searchResultsByQuery())
    }

    fun openUserResults() {
        navigate(destinations.userResults())
    }
}