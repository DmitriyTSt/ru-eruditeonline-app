package ru.eruditeonline.app.data.model.test

/**
 * Тест в выбранной возрастной категории конкурса
 */
class CompetitionTest(
    /** Идентификатор */
    val id: String,
    /** Название конкурса с предметом */
    val title: String,
    /** Возрастная категория */
    val ageCategoryTitle: String?,
    /** Список вопросов */
    val questions: List<Question>,
)
