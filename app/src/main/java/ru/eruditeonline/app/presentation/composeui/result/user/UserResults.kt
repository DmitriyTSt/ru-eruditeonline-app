package ru.eruditeonline.app.presentation.composeui.result.user

import kotlinx.serialization.Serializable
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen

@Serializable
data class UserResults(val email: String? = null) : BaseScreen
