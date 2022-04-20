package ru.eruditeonline.app.presentation.ui.auth.registration

import ru.eruditeonline.app.presentation.navigation.Destination
import javax.inject.Inject

class RegistrationDestinations @Inject constructor() {
    /** Выбор страны */
    fun selectCountry() = Destination.Action(
        RegistrationFragmentDirections.actionRegistrationFragmentToSelectCountryGraph()
    )

    /** Успешная регистрация */
    fun successRegistration() = Destination.Action(
        RegistrationFragmentDirections.actionRegistrationFragmentToRegistrationSuccessFragment()
    )
}
