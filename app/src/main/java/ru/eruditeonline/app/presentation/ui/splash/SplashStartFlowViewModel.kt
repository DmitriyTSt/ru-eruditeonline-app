package ru.eruditeonline.app.presentation.ui.splash

import androidx.lifecycle.LiveData
import ru.eruditeonline.app.domain.usecase.SplashUseCase
import ru.eruditeonline.architecture.presentation.base.BaseViewModel
import ru.eruditeonline.architecture.presentation.base.SingleLiveEvent
import ru.eruditeonline.architecture.presentation.model.LoadableState
import javax.inject.Inject

class SplashStartFlowViewModel @Inject constructor(
    private val splashUseCase: SplashUseCase,
    private val destinations: SplashDestinations,
) : BaseViewModel() {

    /** Начальная загрузка */
    private val _initialFlowLiveEvent = SingleLiveEvent<LoadableState<SplashUseCase.Result>>()
    val initialFlowLiveEvent: LiveData<LoadableState<SplashUseCase.Result>> = _initialFlowLiveEvent

    val isReady: Boolean
        get() = !(initialFlowLiveEvent.value?.isLoading ?: true)

    fun runStartFlow() {
        _initialFlowLiveEvent.launchLoadData {
            val result = splashUseCase.execute(Unit)
            val destination = when (result) {
                is SplashUseCase.Result.AppUpdateScreen -> destinations.appUpdate(result.appUpdate)
                SplashUseCase.Result.MainScreen -> destinations.main()
            }
            navigate(destination)
            result
        }
    }
}
