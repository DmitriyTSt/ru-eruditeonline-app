package ru.eruditeonline.app.presentation.ui.auth.login

import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class LoginDestinations @Inject constructor() {
    fun registration() = Destination.Action(
        LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
    )
}
