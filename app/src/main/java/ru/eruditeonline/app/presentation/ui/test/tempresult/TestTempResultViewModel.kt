package ru.eruditeonline.app.presentation.ui.test.tempresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.data.model.test.TempResult
import ru.eruditeonline.app.domain.usecase.test.CheckTestUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class TestTempResultViewModel @Inject constructor(
    private val checkTestUseCase: CheckTestUseCase,
) : BaseViewModel() {

    /** Проверка прохождения теста */
    private val _tempResultLiveData = MutableLiveData<LoadableState<TempResult>>()
    val tempResultLiveData: LiveData<LoadableState<TempResult>> = _tempResultLiveData

    fun checkTest(data: CompetitionPassData) {
        _tempResultLiveData.launchLoadData(checkTestUseCase.executeFlow(CheckTestUseCase.Params(data)))
    }
}
