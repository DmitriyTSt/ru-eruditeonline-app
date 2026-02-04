package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiTestCommonResultRow(
    /** Дата прохождения */
    @SerialName("date") val date: Long? = null,
    /** Участник */
    @SerialName("username") val username: String? = null,
    /** Регион (населенный пункт) */
    @SerialName("city") val city: String? = null,
    /** Ссылка на иконку страны */
    @SerialName("countryIcon") val countryIcon: String? = null,
    /** Идентификатор конкурса */
    @SerialName("competitionId") val competitionId: Int? = null,
    /** Конкурс */
    @SerialName("competitionTitle") val competitionTitle: String? = null,
    /** Текст результата */
    @SerialName("resultText") val resultText: String? = null,
)
