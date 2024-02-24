package ru.eruditeonline.app.presentation.ui.auth.validationsuccess

import androidx.lifecycle.LiveData
import ru.eruditeonline.app.domain.usecase.auth.ConfirmEmailUseCase
import ru.eruditeonline.app.domain.usecase.base.executeFlow
import ru.eruditeonline.architecture.presentation.base.BaseViewModel
import ru.eruditeonline.architecture.presentation.base.SingleLiveEvent
import ru.eruditeonline.architecture.presentation.model.LoadableState
import javax.inject.Inject

class ValidationSuccessViewModel @Inject constructor(
    private val confirmEmailUseCase: ConfirmEmailUseCase,
    private val destinations: ValidationSuccessDestinations,
) : BaseViewModel() {

    /** Подтверждение почты */
    private val _confirmEmailLiveEvent = SingleLiveEvent<LoadableState<Unit>>()
    val confirmEmailLiveEvent: LiveData<LoadableState<Unit>> = _confirmEmailLiveEvent

    fun confirmEmail(token: String) {
        _confirmEmailLiveEvent.launchLoadData(confirmEmailUseCase.executeFlow(ConfirmEmailUseCase.Params(token)))
    }

    fun openAuth() {
        navigate(destinations.auth())
    }
}
