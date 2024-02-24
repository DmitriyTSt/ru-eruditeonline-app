package ru.eruditeonline.app.presentation.ui.diploma

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.domain.usecase.GetDiplomasUseCase
import ru.eruditeonline.app.domain.usecase.base.executeFlow
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class SelectDiplomaViewModel @Inject constructor(
    private val getDiplomasUseCase: GetDiplomasUseCase,
) : BaseViewModel() {

    /** Виды дипломов */
    private val _diplomasLiveData = MutableLiveData<LoadableState<List<Diploma>>>()
    val diplomasLiveData: LiveData<LoadableState<List<Diploma>>> = _diplomasLiveData

    fun loadDiplomas() {
        _diplomasLiveData.launchLoadData(getDiplomasUseCase.executeFlow(Unit))
    }
}
