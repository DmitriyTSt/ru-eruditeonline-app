package ru.eruditeonline.app.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
    private val destinations: CommonProfileDestinations,
) : BaseViewModel() {

    /** Авторизован ли пользователь */
    private val _isAuthorizedLiveData = MutableLiveData<Boolean>()
    val isAuthorizedLiveData: LiveData<Boolean> = _isAuthorizedLiveData

    fun resolveAuthState() {
        _isAuthorizedLiveData.postValue(preferencesStorage.isSignedIn)
    }

    fun openUserResults() {
        navigate(destinations.userResults())
    }

    fun openCommonResults() {
        navigate(destinations.commonResults())
    }

    fun openSearchResultsByEmail() {
        navigate(destinations.searchResultsByEmail())
    }

    fun reloadStack() {
        navigate(destinations.reloadStack())
    }

    fun openInformation() {
        navigate(destinations.information())
    }

    fun openSettings() {
        navigate(destinations.settings())
    }
}
