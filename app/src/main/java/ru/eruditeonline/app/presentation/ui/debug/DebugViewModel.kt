package ru.eruditeonline.app.presentation.ui.debug

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.domain.usecase.debug.ChangeEndpointUseCase
import ru.eruditeonline.app.domain.usecase.debug.GetDebugDataUseCase
import ru.eruditeonline.app.domain.usecase.debug.SetComposeEnabledUseCase
import ru.eruditeonline.app.presentation.navigation.AppStarter
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import javax.inject.Inject

class DebugViewModel @Inject constructor(
    private val getDebugDataUseCase: GetDebugDataUseCase,
    private val changeEndpointUseCase: ChangeEndpointUseCase,
    private val setComposeEnabledUseCase: SetComposeEnabledUseCase,
    val appStarter: AppStarter,
) : BaseViewModel() {
    /** Данные дебаг экрана */
    private val _debugDataLiveData = MutableLiveData<LoadableState<GetDebugDataUseCase.Result>>()
    val debugDataLiveData: LiveData<LoadableState<GetDebugDataUseCase.Result>> = _debugDataLiveData

    /** Переоткрытие приложения после изменения среды */
    private val _restartAppLiveEvent = SingleLiveEvent<LoadableState<Boolean>>()
    val restartAppLiveEvent: LiveData<LoadableState<Boolean>> = _restartAppLiveEvent

    fun initData() {
        _debugDataLiveData.launchLoadData(getDebugDataUseCase.executeFlow(Unit))
    }

    fun applyNewEndpoint(isDebug: Boolean, isRelease: Boolean, isCustom: Boolean, customEndpoint: String) {
        val endpointData = _debugDataLiveData.value?.getOrNull() ?: return
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

    fun setComposeEnabled(isEnabled: Boolean) {
        _restartAppLiveEvent.launchLoadData {
            setComposeEnabledUseCase.execute(SetComposeEnabledUseCase.Params(isEnabled))
        }
    }
}
