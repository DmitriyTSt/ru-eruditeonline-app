package ru.eruditeonline.app.presentation.ui.auth.validationsuccess

import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ValidationSuccessViewModel @Inject constructor(
    private val destinations: ValidationSuccessDestinations,
) : BaseViewModel() {

    fun openAuth() {
        navigate(destinations.auth())
    }
}