package ru.eruditeonline.app.presentation.ui.test.tempresult

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.data.model.test.CreatedResult
import ru.eruditeonline.app.data.model.test.TempResult
import ru.eruditeonline.app.domain.usecase.result.SaveResultUseCase
import ru.eruditeonline.app.domain.usecase.test.CheckTestUseCase
import ru.eruditeonline.app.presentation.extension.validateAllFields
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import ru.eruditeonline.app.presentation.ui.views.TextInputValidator
import javax.inject.Inject

class TestTempResultViewModel @Inject constructor(
    private val checkTestUseCase: CheckTestUseCase,
    private val saveResultUseCase: SaveResultUseCase,
    private val destinations: TestTempResultDestinations,
) : BaseViewModel() {

    /** Проверка прохождения теста */
    private val _tempResultLiveData = MutableLiveData<LoadableState<TempResult>>()
    val tempResultLiveData: LiveData<LoadableState<TempResult>> = _tempResultLiveData

    /** Страна */
    private val _countryLiveData = MutableLiveData<Country>()
    val countryLiveData: LiveData<Country> = _countryLiveData

    /** Вид диплома */
    private val _diplomaLiveData = MutableLiveData<Diploma>()
    val diplomaLiveData: LiveData<Diploma> = _diplomaLiveData

    /** Скрол до первого поля с ошибкой, передается айди */
    private val _scrollToErrorFieldLiveEvent = SingleLiveEvent<Int>()
    val scrollToErrorFieldLiveEvent: LiveData<Int> = _scrollToErrorFieldLiveEvent

    /** Ошибка валидации email */
    private val _emailValidationErrorLiveEvent = SingleLiveEvent<Int>()
    val emailValidationErrorLiveEvent: LiveData<Int> = _emailValidationErrorLiveEvent

    /** Ошибка согласия на обработку ПД */
    private val _privacyPolicyErrorLiveEvent = SingleLiveEvent<Unit>()
    val privacyPolicyErrorLiveEvent: LiveData<Unit> = _privacyPolicyErrorLiveEvent

    /** Сохранение результата */
    private val _saveResultLiveEvent = SingleLiveEvent<LoadableState<CreatedResult>>()
    val saveResultLiveEvent: LiveData<LoadableState<CreatedResult>> = _saveResultLiveEvent.map { state ->
        state.doOnSuccess { result ->
            navigate(destinations.successSaveResult(result))
        }
        state
    }

    fun checkTest(data: CompetitionPassData) {
        _tempResultLiveData.launchLoadData(checkTestUseCase.executeFlow(CheckTestUseCase.Params(data)))
    }

    fun openSelectCountry() {
        navigate(destinations.selectCountry())
    }

    fun openPersonalData() {
        navigate(destinations.personalData())
    }

    fun selectCountry(country: Country) {
        _countryLiveData.postValue(country)
    }

    fun openSelectDiploma() {
        navigate(destinations.selectDiploma())
    }

    fun selectDiploma(diploma: Diploma) {
        _diplomaLiveData.postValue(diploma)
    }

    fun saveResult(
        surname: TextInputValidator,
        name: TextInputValidator,
        patronymic: TextInputValidator,
        school: TextInputValidator,
        position: TextInputValidator,
        teacher: TextInputValidator,
        country: TextInputValidator,
        city: TextInputValidator,
        region: TextInputValidator,
        email: TextInputValidator,
        teacherEmail: TextInputValidator,
        diploma: TextInputValidator,
        isPrivacyPolicyChecked: Boolean,
        ratingQuality: Int,
        ratingDifficulty: Int,
        ratingInterest: Int,
    ) {
        val tempResultId = tempResultLiveData.value?.getOrNull()?.id ?: return

        val validationResult = validateAllFields(surname, name, city, country, email, diploma)
        if (!validationResult.isValid) {
            validationResult.firstInvalidViewId?.let { _scrollToErrorFieldLiveEvent.postValue(it) }
            return
        }

        if (!email.text.contains(Patterns.EMAIL_ADDRESS.toRegex())) {
            _emailValidationErrorLiveEvent.postValue(email.getLayoutId())
            return
        }

        if (teacherEmail.text.isNotEmpty() && !teacherEmail.text.contains(Patterns.EMAIL_ADDRESS.toRegex())) {
            _emailValidationErrorLiveEvent.postValue(teacherEmail.getLayoutId())
            return
        }

        if (!isPrivacyPolicyChecked) {
            _privacyPolicyErrorLiveEvent.postValue(Unit)
            return
        }

        val countryModel = countryLiveData.value ?: return
        val diplomaModel = diplomaLiveData.value ?: return

        _saveResultLiveEvent.launchLoadData(
            saveResultUseCase.executeFlow(
                SaveResultUseCase.Params(
                    completeId = tempResultId,
                    name = name.text,
                    surname = surname.text,
                    patronymic = patronymic.text,
                    school = school.text,
                    position = position.text,
                    teacher = teacher.text,
                    country = countryModel.code,
                    city = city.text,
                    region = region.text,
                    email = email.text,
                    teacherEmail = teacherEmail.text,
                    diplomaType = diplomaModel.type,
                    quality = ratingQuality,
                    difficulty = ratingDifficulty,
                    interest = ratingInterest,
                )
            )
        )
    }
}
