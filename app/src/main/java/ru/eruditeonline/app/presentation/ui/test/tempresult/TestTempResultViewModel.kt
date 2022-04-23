package ru.eruditeonline.app.presentation.ui.test.tempresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.data.model.test.TempResult
import ru.eruditeonline.app.domain.usecase.result.SaveResultUseCase
import ru.eruditeonline.app.domain.usecase.test.CheckTestUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
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

    fun checkTest(data: CompetitionPassData) {
        _tempResultLiveData.launchLoadData(checkTestUseCase.executeFlow(CheckTestUseCase.Params(data)))
    }

    fun openSelectCountry() {
        navigate(destinations.selectCountry())
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
}
