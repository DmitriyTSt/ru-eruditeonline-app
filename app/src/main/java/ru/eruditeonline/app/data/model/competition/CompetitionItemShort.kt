package ru.eruditeonline.app.data.model.competition

/**
 * Сокращенная модель конкурса для списков
 */
class CompetitionItemShort(
    /** Идентификатор теста */
    val id: Int,
    /** Название теста */
    val title: String,
    /** Предмет(ы) */
    val subject: String,
    /** Возрастные группы */
    val ages: String,
    /** Сложность (1-5) */
    val difficulty: Int,
    /** Ссылка на изображение */
    val icon: String?,
)
