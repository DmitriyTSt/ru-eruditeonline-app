package ru.eruditeonline.app.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.map
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.domain.usecase.SplashUseCase
import ru.eruditeonline.app.domain.usecase.debug.IsDebugButtonVisibleUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val splashUseCase: SplashUseCase,
    private val destinations: SplashDestinations,
    private val isDebugButtonVisibleUseCase: IsDebugButtonVisibleUseCase,
) : BaseViewModel() {
    /** Начальная инициализация приложения */
    private val _startFlowLiveEvent = SingleLiveEvent<LoadableState<SplashUseCase.Result>>()
    val startFlowLiveEvent: LiveData<LoadableState<SplashUseCase.Result>> = _startFlowLiveEvent

    /** Видимость кнопки дебага */
    private val _isDebugButtonVisibleLiveData = MutableLiveData<LoadableState<Boolean>>()
    val isDebugButtonVisibleLiveData: LiveData<LoadableState<Boolean>> = _isDebugButtonVisibleLiveData

    fun runStartFlow() {
        _startFlowLiveEvent.launchLoadData(
            splashUseCase.executeFlow(Unit).map { state ->
                state.doOnSuccess { result ->
                    val destination = when (result) {
                        is SplashUseCase.Result.AppUpdateScreen -> destinations.appUpdate(result.appUpdate)
                        SplashUseCase.Result.MainScreen -> destinations.main()
                    }
                    navigate(destination)
                }
                state
            }
        )
    }

    fun initDebugButton() {
        _isDebugButtonVisibleLiveData.launchLoadData(isDebugButtonVisibleUseCase.executeFlow(Unit))
    }

    fun openDebug() {
        navigate(destinations.debug())
    }
}
