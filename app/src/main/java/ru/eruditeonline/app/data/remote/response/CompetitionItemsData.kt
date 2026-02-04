package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionFilters
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionItemShort

@Serializable
data class CompetitionItemsData(
    @SerialName("list") val list: List<ApiCompetitionItemShort>? = null,
    @SerialName("hasMore") val hasMore: Boolean? = null,
    @SerialName("filters") val filters: ApiCompetitionFilters? = null,
)
