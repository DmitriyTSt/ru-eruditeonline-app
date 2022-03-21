package ru.eruditeonline.app.data.remote.response

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.test.ApiCompetitionTest

class CompetitionTestData(
    @SerializedName("test") val test: ApiCompetitionTest?,
)
