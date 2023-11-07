package ru.eruditeonline.app.presentation.managers

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import javax.inject.Inject

class ComposeFeatureManager @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
) {
    val isComposeEnabled: Boolean
        get() = preferencesStorage.isComposeEnabled
}