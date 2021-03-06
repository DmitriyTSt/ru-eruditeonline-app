package ru.eruditeonline.app.presentation.ui.auth.login

import androidx.lifecycle.LiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.domain.usecase.auth.LoginUseCase
import ru.eruditeonline.app.presentation.extension.validateAllFields
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import ru.eruditeonline.app.presentation.ui.views.TextInputValidator
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val destinations: LoginDestinations,
) : BaseViewModel() {

    /** Авторизация */
    private val _loginLiveEvent = SingleLiveEvent<LoadableState<Unit>>()
    val loginLiveEvent: LiveData<LoadableState<Unit>> = _loginLiveEvent

    fun login(login: TextInputValidator, password: TextInputValidator) {
        if (!validateAllFields(login, password).isValid) return

        _loginLiveEvent.launchLoadData(loginUseCase.executeFlow(LoginUseCase.Params(login.text, password.text)))
    }

    fun openRegistration() {
        navigate(destinations.registration())
    }

    fun openRestorePassword() {
        navigate(destinations.restorePassword())
    }
}
