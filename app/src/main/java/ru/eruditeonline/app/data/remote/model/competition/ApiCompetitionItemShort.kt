package ru.eruditeonline.app.data.remote.model.competition

import com.google.gson.annotations.SerializedName

/**
 * Сокращенная модель конкурса для списков
 */
class ApiCompetitionItemShort(
    /** Идентификатор теста */
    @SerializedName("id") val id: Int?,
    /** Название теста */
    @SerializedName("title") val title: String?,
    /** Предмет(ы) */
    @SerializedName("subject") val subject: String?,
    /** Возрастные группы */
    @SerializedName("ages") val ages: String?,
    /** Сложность (1-5) */
    @SerializedName("difficulty") val difficulty: Int?,
    /** Ссылка на изображение */
    @SerializedName("icon") val icon: String?,
)
