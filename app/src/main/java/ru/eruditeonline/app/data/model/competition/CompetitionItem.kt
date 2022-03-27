package ru.eruditeonline.app.data.model.competition

/**
 * Конкурс с тестами
 */
class CompetitionItem(
    /** Идентификатор конкурса */
    val id: Int,
    /** Полное название конкурса */
    val title: String,
    /** Ссылка на изображение теста */
    val icon: String?,
    /** Сложность (от 1 до 5) */
    val difficulty: Int,
    /** Список тестов в конкурсе */
    val tests: List<TestAgeGroup>,
    /** Аннотация */
    val annotation: String?,
    /** Описание */
    val description: String?,
    /** Дополнительные материалы, могут содержать ссылки */
    val infos: List<String>,
)
