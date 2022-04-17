package ru.eruditeonline.app.presentation.ui.profile

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val preferencesStorage: PreferencesStorage,
) : BaseViewModel()
