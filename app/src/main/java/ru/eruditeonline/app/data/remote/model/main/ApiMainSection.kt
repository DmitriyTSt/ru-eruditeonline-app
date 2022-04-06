package ru.eruditeonline.app.data.remote.model.main

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionItemShort

class ApiMainSection(
    @SerializedName("type") val type: ApiMainSectionType?,
    @SerializedName("taglines") val taglines: List<ApiTagline>?,
    @SerializedName("title") val title: String?,
    @SerializedName("competitionViewType") val competitionViewType: ApiCompetitionViewType?,
    @SerializedName("competitionItems") val competitionItems: List<ApiCompetitionItemShort>?,
)
