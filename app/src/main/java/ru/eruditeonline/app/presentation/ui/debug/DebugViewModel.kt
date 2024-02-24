package ru.eruditeonline.app.presentation.ui.debug

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.domain.usecase.base.executeFlow
import ru.eruditeonline.app.domain.usecase.debug.ChangeEndpointUseCase
import ru.eruditeonline.app.domain.usecase.debug.GetEndpointDataUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import javax.inject.Inject

class DebugViewModel @Inject constructor(
    private val getEndpointDataUseCase: GetEndpointDataUseCase,
    private val changeEndpointUseCase: ChangeEndpointUseCase,
) : BaseViewModel() {
    /** Данные сервера */
    private val _endpointDataLiveData = MutableLiveData<LoadableState<GetEndpointDataUseCase.Result>>()
    val endpointDataLiveData: LiveData<LoadableState<GetEndpointDataUseCase.Result>> = _endpointDataLiveData

    /** Отображение выбора Endpoint - Dev */
    val showDevEndpointLiveEvent: LiveData<Boolean> = _endpointDataLiveData.map { state ->
        if (state.isSuccess) {
            (state as LoadableState.Success).data.let { data ->
                data.currentEndpoint == data.devEndpoint
            }
        } else {
            false
        }
    }

    /** Отображение выбора Endpoint - Release */
    val showReleaseEndpointLiveEvent: LiveData<Boolean> = _endpointDataLiveData.map { state ->
        if (state.isSuccess) {
            (state as LoadableState.Success).data.let { data ->
                data.currentEndpoint == data.prodEndpoint
            }
        } else {
            false
        }
    }

    /** Отображение выбора Endpoint - Custom */
    val showCustomEndpointLiveEvent: LiveData<String> = _endpointDataLiveData.map { state ->
        if (state.isSuccess) {
            (state as LoadableState.Success).data.let { data ->
                if (data.currentEndpoint != data.prodEndpoint && data.currentEndpoint != data.devEndpoint) {
                    data.currentEndpoint
                } else {
                    ""
                }
            }
        } else {
            ""
        }
    }

    /** Переоткрытие приложения после изменения среды */
    private val _restartAppLiveEvent = SingleLiveEvent<LoadableState<Boolean>>()
    val restartAppLiveEvent: LiveData<LoadableState<Boolean>> = _restartAppLiveEvent

    fun initEndpoint() {
        _endpointDataLiveData.launchLoadData(getEndpointDataUseCase.executeFlow(Unit))
    }

    fun applyNewEndpoint(isDebug: Boolean, isRelease: Boolean, isCustom: Boolean, customEndpoint: String) {
        val endpointData = _endpointDataLiveData.value?.getOrNull() ?: return
        val newEndpoint = when {
            isDebug -> endpointData.devEndpoint
            isRelease -> endpointData.prodEndpoint
            isCustom -> customEndpoint
            else -> null
        }
        _restartAppLiveEvent.launchLoadData(
            changeEndpointUseCase.executeFlow(ChangeEndpointUseCase.Params(newEndpoint))
        )
    }
}
