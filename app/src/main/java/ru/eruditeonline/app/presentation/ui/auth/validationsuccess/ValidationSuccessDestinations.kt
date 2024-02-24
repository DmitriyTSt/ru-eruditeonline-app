package ru.eruditeonline.app.presentation.ui.auth.validationsuccess

import ru.eruditeonline.app.presentation.navigation.Action
import ru.eruditeonline.architecture.presentation.navigation.Destination
import ru.eruditeonline.architecture.presentation.navigation.Destinations
import javax.inject.Inject

class ValidationSuccessDestinations @Inject constructor() : Destinations {
    /** Вход */
    fun auth() = Destination.Action(
        ValidationSuccessFragmentDirections.actionValidationSuccessFragmentToLoginFragment()
    )
}
