package ru.eruditeonline.app.presentation.ui.auth.registration

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.auth.Gender
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.domain.usecase.auth.RegistrationUseCase
import ru.eruditeonline.app.presentation.extension.validateAllFields
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import ru.eruditeonline.app.presentation.ui.views.TextInputValidator
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val destinations: RegistrationDestinations,
) : BaseViewModel() {

    /** День рождения */
    private val _birthdayLiveData = MutableLiveData<Long>()
    val birthdayLiveData: LiveData<Long> = _birthdayLiveData

    /** Страна */
    private val _countryLiveData = MutableLiveData<Country>()
    val countryLiveData: LiveData<Country> = _countryLiveData

    /** Скрол до первого поля с ошибкой, передается айди */
    private val _scrollToErrorFieldLiveEvent = SingleLiveEvent<Int>()
    val scrollToErrorFieldLiveEvent: LiveData<Int> = _scrollToErrorFieldLiveEvent

    /** Ошибка подтверждения пароля */
    private val _passwordConfirmErrorLiveEvent = SingleLiveEvent<Unit>()
    val passwordConfirmErrorLiveEvent: LiveData<Unit> = _passwordConfirmErrorLiveEvent

    /** Ошибка валидации email */
    private val _emailValidationErrorLiveEvent = SingleLiveEvent<Unit>()
    val emailValidationErrorLiveEvent: LiveData<Unit> = _emailValidationErrorLiveEvent

    /** Ошибка согласия на обработку ПД */
    private val _privacyPolicyErrorLiveEvent = SingleLiveEvent<Unit>()
    val privacyPolicyErrorLiveEvent: LiveData<Unit> = _privacyPolicyErrorLiveEvent

    fun openSelectCountry() {
        navigate(destinations.selectCountry())
    }

    fun selectCountry(country: Country) {
        _countryLiveData.postValue(country)
    }

    fun selectBirthday(millis: Long) {
        _birthdayLiveData.postValue(millis)
    }

    fun register(
        surname: TextInputValidator,
        name: TextInputValidator,
        patronymic: TextInputValidator,
        gender: Gender,
        school: TextInputValidator,
        city: TextInputValidator,
        region: TextInputValidator,
        country: TextInputValidator,
        email: TextInputValidator,
        password: TextInputValidator,
        passwordConfirm: TextInputValidator,
        isNotificationChecked: Boolean,
        isPrivacyPolicyChecked: Boolean,
    ) {
        val validationResult = validateAllFields(surname, name, city, country, email, password, passwordConfirm)
        if (!validationResult.isValid) {
            validationResult.firstInvalidViewId?.let { _scrollToErrorFieldLiveEvent.postValue(it) }
            return
        }

        if (!email.text.contains(Patterns.EMAIL_ADDRESS.toRegex())) {
            _emailValidationErrorLiveEvent.postValue(Unit)
            return
        }

        if (password.text != passwordConfirm.text) {
            _passwordConfirmErrorLiveEvent.postValue(Unit)
            return
        }

        if (!isPrivacyPolicyChecked) {
            _privacyPolicyErrorLiveEvent.postValue(Unit)
            return
        }
    }
}
