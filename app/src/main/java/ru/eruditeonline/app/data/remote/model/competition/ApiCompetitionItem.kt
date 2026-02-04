package ru.eruditeonline.app.data.remote.model.competition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Конкурс с тестами
 */
@Serializable
data class ApiCompetitionItem(
    /** Идентификатор конкурса */
    @SerialName("id") val id: Int? = null,
    /** Полное название конкурса */
    @SerialName("title") val title: String? = null,
    /** Предмет */
    @SerialName("subject") val subject: String? = null,
    /** Возрастная категория */
    @SerialName("ages") val ages: String? = null,
    /** Ссылка на изображение теста */
    @SerialName("icon") val icon: String? = null,
    /** Сложность (от 1 до 5) */
    @SerialName("difficulty") val difficulty: Int? = null,
    /** Список тестов в конкурсе */
    @SerialName("tests") val tests: List<ApiTestAgeGroup>? = null,
    /** Аннотация */
    @SerialName("annotation") val annotation: String? = null,
    /** Описание */
    @SerialName("description") val description: String? = null,
    /** Дополнительные материалы, могут содержать ссылки */
    @SerialName("infos") val infos: List<String>? = null,
)
