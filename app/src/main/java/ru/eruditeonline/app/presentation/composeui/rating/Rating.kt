package ru.eruditeonline.app.presentation.composeui.rating

import kotlinx.serialization.Serializable
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen
import ru.eruditeonline.app.presentation.composeui.base.ScreenWithBottomNavigation

@Serializable
data object Rating : BaseScreen, ScreenWithBottomNavigation
