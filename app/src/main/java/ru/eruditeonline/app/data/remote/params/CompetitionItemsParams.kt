package ru.eruditeonline.app.data.remote.params

import com.google.gson.annotations.SerializedName

class CompetitionItemsParams(
    /** Поисковой запрос */
    @SerializedName("query") val query: String?,
    /** Список идентификаторов возрастной категории для фильтрации */
    @SerializedName("ageIds") val ageIds: List<String>?,
    /** Список идентификаторов предметов для фильтрации */
    @SerializedName("subjectIds") val subjectIds: List<String>?,
    /** Смещение */
    @SerializedName("offset") val offset: Int,
    /** Количество */
    @SerializedName("limit") val limit: Int,
)
