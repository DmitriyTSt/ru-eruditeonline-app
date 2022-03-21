package ru.eruditeonline.app.data.remote.response

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.competition.ApiCompetitionItem

class CompetitionItemData(
    @SerializedName("item") val item: ApiCompetitionItem?,
)
