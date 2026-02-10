package ru.eruditeonline.app.presentation.composeui.result.info

import kotlinx.serialization.Serializable
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen

@Serializable
data class Info(
    val path: String = "",
) : BaseScreen
