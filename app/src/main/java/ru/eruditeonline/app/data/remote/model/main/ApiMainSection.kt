package ru.eruditeonline.app.data.remote.model.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionItemShort

@Serializable
data class ApiMainSection(
    @SerialName("type") val type: ApiMainSectionType? = null,
    @SerialName("taglines") val taglines: List<ApiTagline>? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("competitionViewType") val competitionViewType: ApiCompetitionViewType? = null,
    @SerialName("competitionItems") val competitionItems: List<ApiCompetitionItemShort>? = null,
)
