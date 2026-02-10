package ru.eruditeonline.app.presentation.composeui.profile

import kotlinx.serialization.Serializable
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen
import ru.eruditeonline.app.presentation.composeui.base.ScreenWithBottomNavigation

@Serializable
data object Profile : BaseScreen, ScreenWithBottomNavigation
