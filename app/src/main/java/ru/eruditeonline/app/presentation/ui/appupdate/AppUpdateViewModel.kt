package ru.eruditeonline.app.presentation.ui.appupdate

import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class AppUpdateViewModel @Inject constructor(
    private val destinations: AppUpdateDestinations,
) : BaseViewModel() {

    fun openAppUpdate() {
        navigate(destinations.googlePlayAppPage())
    }

    fun openMainScreen() {
        navigate(destinations.main())
    }
}
