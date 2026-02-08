package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.test.ApiCompetitionTest

@Serializable
data class CompetitionTestData(
    @SerialName("test") val test: ApiCompetitionTest? = null,
)
