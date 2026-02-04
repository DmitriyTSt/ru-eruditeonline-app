package ru.eruditeonline.app.data.remote.model.competition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Сокращенная модель конкурса для списков
 */
@Serializable
data class ApiCompetitionItemShort(
    /** Идентификатор теста */
    @SerialName("id") val id: Int? = null,
    /** Название теста */
    @SerialName("title") val title: String? = null,
    /** Предмет(ы) */
    @SerialName("subject") val subject: String? = null,
    /** Возрастные группы */
    @SerialName("ages") val ages: String? = null,
    /** Сложность (1-5) */
    @SerialName("difficulty") val difficulty: Int? = null,
    /** Ссылка на изображение */
    @SerialName("icon") val icon: String? = null,
)
