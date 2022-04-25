package ru.eruditeonline.app.presentation.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.presentation.managers.Theme
import ru.eruditeonline.app.presentation.managers.ThemeManager
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val themeManager: ThemeManager,
    private val destinations: SettingsDestinations,
) : BaseViewModel() {

    /** Данные настроек */
    private val _dataLiveData = MutableLiveData<LoadableState<SettingsData>>()
    val dataLiveData: LiveData<LoadableState<SettingsData>> = _dataLiveData

    /** Диалог о смене темы */
    private val _themeChangedLiveEvent = SingleLiveEvent<Unit>()
    val themeChangedLiveEvent: LiveData<Unit> = _themeChangedLiveEvent

    fun loadData() {
        _dataLiveData.postValue(LoadableState.Success(SettingsData(themeManager.theme)))
    }

    fun changeTheme(theme: Theme) {
        if (_dataLiveData.value?.getOrNull()?.theme != theme) {
            themeManager.theme = theme
            loadData()
            _themeChangedLiveEvent.postValue(Unit)
        }
    }

    fun restartApp() {
        navigate(destinations.restartApp())
    }
}
