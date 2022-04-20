package ru.eruditeonline.app.presentation.ui.auth.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.domain.usecase.auth.RegistrationUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
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

    fun openSelectCountry() {
        navigate(destinations.selectCountry())
    }

    fun selectCountry(country: Country) {
        _countryLiveData.postValue(country)
    }

    fun selectBirthday(millis: Long) {
        _birthdayLiveData.postValue(millis)
    }
}
