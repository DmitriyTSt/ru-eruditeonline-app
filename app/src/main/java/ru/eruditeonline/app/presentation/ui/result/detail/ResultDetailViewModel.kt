package ru.eruditeonline.app.presentation.ui.result.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.test.TestUserResult
import ru.eruditeonline.app.domain.usecase.base.executeFlow
import ru.eruditeonline.app.domain.usecase.result.GetResultUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ResultDetailViewModel @Inject constructor(
    private val getResultUseCase: GetResultUseCase,
) : BaseViewModel() {
    /** Результат */
    private val _resultLiveData = MutableLiveData<LoadableState<TestUserResult>>()
    val resultLiveData: LiveData<LoadableState<TestUserResult>> = _resultLiveData

    fun loadResult(id: Int) {
        _resultLiveData.launchLoadData(getResultUseCase.executeFlow(GetResultUseCase.Params(id)))
    }
}
