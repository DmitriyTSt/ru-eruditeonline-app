package ru.eruditeonline.app.presentation.ui.profile

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.architecture.presentation.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val preferencesStorage: PreferencesStorage,
) : BaseViewModel()
