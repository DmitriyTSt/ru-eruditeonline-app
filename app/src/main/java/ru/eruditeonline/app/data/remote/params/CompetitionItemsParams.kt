package ru.eruditeonline.app.data.remote.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompetitionItemsParams(
    /** Поисковой запрос */
    @SerialName("query") val query: String? = null,
    /** Список идентификаторов возрастной категории для фильтрации */
    @SerialName("ageIds") val ageIds: List<String>? = null,
    /** Список идентификаторов предметов для фильтрации */
    @SerialName("subjectIds") val subjectIds: List<String>? = null,
    /** Смещение */
    @SerialName("offset") val offset: Int,
    /** Количество */
    @SerialName("limit") val limit: Int,
)
