package ru.eruditeonline.app.presentation.ui.profile.anonym

import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class AnonymProfileViewModel @Inject constructor(
    private val destinations: AnonymProfileDestinations,
) : BaseViewModel() {

    fun openCommonResults() {
        navigate(destinations.commonResults())
    }

    fun openLogin() {
        navigate(destinations.login())
    }

    fun openRegistration() {
        navigate(destinations.registration())
    }

    fun openSearchResultsByEmail() {
        navigate(destinations.searchResultsByEmail())
    }

    fun reloadStack() {
        navigate(destinations.reloadStack())
    }

    fun openInformation() {
        navigate(destinations.information())
    }

    fun openSettings() {
        navigate(destinations.settings())
    }
}
