package ru.eruditeonline.app.presentation.composeui.competition.detail

import kotlinx.serialization.Serializable
import ru.eruditeonline.app.presentation.composeui.base.BaseScreen

@Serializable
data class Competition(
    val id: Int,
) : BaseScreen
