package ru.eruditeonline.app.presentation.ui.auth.registration

import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val destinations: RegistrationDestinations,
) : BaseViewModel() {

    fun openSelectCountry() {
        navigate(destinations.selectCountry())
    }
}
