package ru.eruditeonline.app.presentation.ui.profile.anonym

import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class AnonymProfileViewModel @Inject constructor(
    private val destinations: AnonymProfileDestinations,
) : BaseViewModel() {

    fun openLogin() {
        navigate(destinations.login())
    }

    fun openRegistration() {
        navigate(destinations.registration())
    }
}
