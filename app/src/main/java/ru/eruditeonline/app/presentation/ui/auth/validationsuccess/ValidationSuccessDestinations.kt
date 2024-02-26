package ru.eruditeonline.app.presentation.ui.auth.validationsuccess

import ru.eruditeonline.navigation.Action
import ru.eruditeonline.navigation.Destination
import ru.eruditeonline.navigation.Destinations
import javax.inject.Inject

class ValidationSuccessDestinations @Inject constructor() : Destinations {
    /** Вход */
    fun auth() = Destination.Action(
        ValidationSuccessFragmentDirections.actionValidationSuccessFragmentToLoginFragment()
    )
}
