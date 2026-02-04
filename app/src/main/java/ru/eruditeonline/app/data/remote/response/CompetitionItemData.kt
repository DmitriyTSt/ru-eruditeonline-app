package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionItem

@Serializable
data class CompetitionItemData(
    @SerialName("item") val item: ApiCompetitionItem? = null,
)
