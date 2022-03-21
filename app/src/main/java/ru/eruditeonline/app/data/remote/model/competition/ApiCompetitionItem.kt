package ru.eruditeonline.app.data.remote.model.competition

import com.google.gson.annotations.SerializedName

/**
 * Конкурс с тестами
 */
class ApiCompetitionItem(
    /** Идентификатор конкурса */
    @SerializedName("id") val id: String?,
    /** Полное название конкурса */
    @SerializedName("title") val title: String?,
    /** Ссылка на изображение теста */
    @SerializedName("icon") val icon: String?,
    /** Сложность (от 1 до 5) */
    @SerializedName("difficulty") val difficulty: Int?,
    /** Список тестов в конкурсе */
    @SerializedName("tests") val tests: List<ApiTestAgeGroup?>?,
    /** Аннотация */
    @SerializedName("annotation") val annotation: String?,
    /** Описание */
    @SerializedName("description") val description: String?,
    /** Дополнительные материалы, могут содержать ссылки */
    @SerializedName("infos") val infos: List<String?>?,
)
