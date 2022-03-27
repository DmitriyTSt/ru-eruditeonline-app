package ru.eruditeonline.app.data.remote.response

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionFilters
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionItemShort

class CompetitionItemsData(
    @SerializedName("list") val list: List<ApiCompetitionItemShort>?,
    @SerializedName("hasMore") val hasMore: Boolean?,
    @SerializedName("filters") val filters: ApiCompetitionFilters?,
)
