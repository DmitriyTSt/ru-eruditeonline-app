package ru.eruditeonline.app.data.remote.model.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiCompetitionViewType {
    @SerialName("ROW") ROW,
    @SerialName("CARD") CARD,
}
