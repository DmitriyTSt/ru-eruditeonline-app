package ru.eruditeonline.app.presentation.ui.profile

import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val destinations: ProfileDestinations,
) : BaseViewModel() {
    fun openCommonResults() {
        navigate(destinations.commonResults())
    }

    fun openSearchResults() {
        navigate(destinations.searchResults())
    }
}
