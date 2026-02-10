package ru.eruditeonline.app.presentation.composeui.webpage

import kotlinx.serialization.Serializable
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen

@Serializable
data class WebPage(
    val path: String = "",
) : BaseScreen
