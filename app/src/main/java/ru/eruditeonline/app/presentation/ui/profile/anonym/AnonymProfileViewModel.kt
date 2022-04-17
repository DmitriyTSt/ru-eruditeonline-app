package ru.eruditeonline.app.presentation.ui.profile.anonym

import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class AnonymProfileViewModel @Inject constructor(
    private val destinations: AnonymProfileDestinations,
) : BaseViewModel() {

    fun openCommonResults() {
        navigate(destinations.commonResults())
    }

    fun openAuth() {
        navigate(destinations.login())
    }

    fun openSearchResultsByEmail() {
        navigate(destinations.searchResultsByEmail())
    }
}