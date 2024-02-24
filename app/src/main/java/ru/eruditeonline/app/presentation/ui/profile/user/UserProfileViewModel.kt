package ru.eruditeonline.app.presentation.ui.profile.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.profile.Profile
import ru.eruditeonline.app.domain.usecase.auth.LogoutUseCase
import ru.eruditeonline.app.domain.usecase.base.executeFlow
import ru.eruditeonline.app.domain.usecase.profile.GetProfileUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import ru.eruditeonline.app.presentation.ui.base.SingleLiveEvent
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val destinations: UserProfileDestinations,
) : BaseViewModel() {
    /** Профиль */
    private val _profileLiveData = MutableLiveData<LoadableState<Profile>>()
    val profileLiveData: LiveData<LoadableState<Profile>> = _profileLiveData

    /** Выход */
    private val _logoutLiveEvent = SingleLiveEvent<LoadableState<Unit>>()
    val logoutLiveEvent: LiveData<LoadableState<Unit>> = _logoutLiveEvent

    fun loadProfile() {
        _profileLiveData.launchLoadData(getProfileUseCase.executeFlow(Unit))
    }

    fun logout() {
        _logoutLiveEvent.launchLoadData(logoutUseCase.executeFlow(Unit))
    }

    fun openCommonResults() {
        navigate(destinations.commonResults())
    }

    fun openSearchResultsByEmail() {
        navigate(destinations.searchResultsByEmail())
    }

    fun openUserResults() {
        navigate(destinations.userResults())
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
