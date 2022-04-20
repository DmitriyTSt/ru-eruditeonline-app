package ru.eruditeonline.app.presentation.ui.auth.validationsuccess

import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class ValidationSuccessDestinations @Inject constructor() {
    /** Вход */
    fun auth() = Destination.Action(
        ValidationSuccessFragmentDirections.actionValidationSuccessFragmentToLoginFragment()
    )
}
