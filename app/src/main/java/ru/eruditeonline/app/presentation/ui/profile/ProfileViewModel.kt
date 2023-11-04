package ru.eruditeonline.app.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) : BaseViewModel() {

    /** Авторизован ли пользователь */
    private val _isAuthorizedLiveData = MutableLiveData<Boolean>()
    val isAuthorizedLiveData: LiveData<Boolean> = _isAuthorizedLiveData

    fun resolveAuthState() {
        _isAuthorizedLiveData.postValue(preferencesStorage.isSignedIn)
    }
}
