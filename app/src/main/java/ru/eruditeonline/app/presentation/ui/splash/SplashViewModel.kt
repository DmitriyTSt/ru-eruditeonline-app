package ru.eruditeonline.app.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.domain.usecase.base.executeFlow
import ru.eruditeonline.app.domain.usecase.debug.IsDebugButtonVisibleUseCase
import ru.eruditeonline.architecture.presentation.base.BaseViewModel
import ru.eruditeonline.architecture.presentation.model.LoadableState
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val destinations: SplashDestinations,
    private val isDebugButtonVisibleUseCase: IsDebugButtonVisibleUseCase,
) : BaseViewModel() {

    /** Видимость кнопки дебага */
    private val _isDebugButtonVisibleLiveData = MutableLiveData<LoadableState<Boolean>>()
    val isDebugButtonVisibleLiveData: LiveData<LoadableState<Boolean>> = _isDebugButtonVisibleLiveData

    fun initDebugButton() {
        _isDebugButtonVisibleLiveData.launchLoadData(isDebugButtonVisibleUseCase.executeFlow(Unit))
    }

    fun openDebug() {
        navigate(destinations.debug())
    }
}
