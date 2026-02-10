package ru.eruditeonline.app.presentation.composeui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.eruditeonline.app.domain.usecase.theme.GetComposeThemeUseCase
import ru.eruditeonline.app.domain.usecase.theme.SetComposeThemeUseCase
import ru.eruditeonline.app.presentation.composeui.theme.EruditeThemeModel
import javax.inject.Inject

class ComposeSettingsViewModel @Inject constructor(
    private val getComposeThemeUseCase: GetComposeThemeUseCase,
    private val setComposeThemeUseCase: SetComposeThemeUseCase,
) : ViewModel() {

    private val _currentThemeLiveData = MutableLiveData(EruditeThemeModel.DEFAULT)
    val currentThemeLiveData: LiveData<EruditeThemeModel> = _currentThemeLiveData

    init {
        viewModelScope.launch {
            val theme = getComposeThemeUseCase.execute(Unit).toThemeModel()
            _currentThemeLiveData.value = theme
        }
    }

    fun changeTheme(theme: EruditeThemeModel) {
        if (_currentThemeLiveData.value == theme) return
        _currentThemeLiveData.value = theme
        viewModelScope.launch {
            setComposeThemeUseCase.execute(SetComposeThemeUseCase.Params(theme = theme.name))
        }
    }

    private fun String?.toThemeModel(): EruditeThemeModel {
        return runCatching {
            this?.let { EruditeThemeModel.valueOf(it) } ?: EruditeThemeModel.DEFAULT
        }.getOrDefault(EruditeThemeModel.DEFAULT)
    }
}
