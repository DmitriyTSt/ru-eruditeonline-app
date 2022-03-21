package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName

/**
 * Тест в выбранной возрастной категории конкурса
 */
class ApiCompetitionTest(
    /** Идентификатор */
    @SerializedName("id") val id: String?,
    /** Название конкурса с предметом */
    @SerializedName("title") val title: String?,
    /** Возрастная категория */
    @SerializedName("ageCategoryTitle") val ageCategoryTitle: String?,
    /** Список вопросов */
    @SerializedName("questions") val questions: List<ApiQuestion?>?,
)
