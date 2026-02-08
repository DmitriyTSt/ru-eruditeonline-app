package ru.eruditeonline.app.data.remote.model.competition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCompetitionFilters(
    /** Список фильтрации по возрасту */
    @SerialName("ages") val ages: List<ApiFilterItem>? = null,
    /** Список фильтрации по предметам */
    @SerialName("subjects") val subjects: List<ApiFilterItem>? = null,
)
