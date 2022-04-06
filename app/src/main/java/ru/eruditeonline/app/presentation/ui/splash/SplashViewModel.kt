package ru.eruditeonline.app.presentation.ui.splash

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.map
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.domain.usecase.SplashUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val splashUseCase: SplashUseCase,
    private val destinations: SplashDestinations,
) : BaseViewModel() {
    /** Начальная инициализация приложения */
    private val _startFlowLiveEvent = SingleLiveEvent<LoadableState<Unit>>()
    val startFlowLiveEvent: LiveData<LoadableState<Unit>> = _startFlowLiveEvent

    fun runStartFlow() {
        _startFlowLiveEvent.launchLoadData(
            splashUseCase.executeFlow(Unit).map { state ->
                if (state.isSuccess) {
                    navigate(destinations.main())
                }
                state
            }
        )
    }
}
