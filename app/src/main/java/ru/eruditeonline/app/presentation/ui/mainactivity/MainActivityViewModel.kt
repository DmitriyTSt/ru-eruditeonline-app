package ru.eruditeonline.app.presentation.ui.mainactivity

import androidx.lifecycle.LiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.domain.usecase.SplashUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import ru.eruditeonline.app.presentation.ui.splash.SplashDestinations
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val splashUseCase: SplashUseCase,
    private val destinations: SplashDestinations,
) : BaseViewModel() {

    /** Начальная загрузка */
    private val _initialFlowLiveEvent = SingleLiveEvent<LoadableState<SplashUseCase.Result>>()
    val initialFlowLiveEvent: LiveData<LoadableState<SplashUseCase.Result>> = _initialFlowLiveEvent

    val isReady: Boolean
        get() = !(initialFlowLiveEvent.value?.isLoading ?: true)

    init {
        runStartFlow()
    }

    fun repeatStartFlow() {
        runStartFlow()
    }

    private fun runStartFlow() {
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