package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Тест в выбранной возрастной категории конкурса
 */
@Serializable
data class ApiCompetitionTest(
    /** Идентификатор */
    @SerialName("id") val id: String? = null,
    /** Название конкурса с предметом */
    @SerialName("title") val title: String? = null,
    /** Возрастная категория */
    @SerialName("ageCategoryTitle") val ageCategoryTitle: String? = null,
    /** Список вопросов */
    @SerialName("questions") val questions: List<ApiQuestion>? = null,
)
