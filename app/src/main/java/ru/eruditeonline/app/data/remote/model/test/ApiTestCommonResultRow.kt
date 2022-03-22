package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName

class ApiTestCommonResultRow(
    /** Дата прохождения */
    @SerializedName("date") val date: String?,
    /** Участник */
    @SerializedName("username") val username: String?,
    /** Регион (населенный пункт) */
    @SerializedName("city") val city: String?,
    /** Ссылка на иконку страны */
    @SerializedName("countryIcon") val countryIcon: String?,
    /** Идентификатор конкурса */
    @SerializedName("competitionId") val competitionId: String?,
    /** Конкурс */
    @SerializedName("competitionTitle") val competitionTitle: String?,
    /** Текст результата */
    @SerializedName("resultText") val resultText: String?,
)
