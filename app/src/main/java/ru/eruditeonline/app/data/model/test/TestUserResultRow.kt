package ru.eruditeonline.app.data.model.test

import ru.eruditeonline.app.data.model.base.Score

class TestUserResultRow(
    /** Идентификатор результата */
    val id: String,
    /** Дата прохождения */
    val date: String,
    /** Участник */
    val username: String,
    /** Идентификатор теста */
    val testId: String,
    /** Название конкурса */
    val competitionTitle: String,
    /** Место */
    place: String,
    /** Балл */
    val score: Score,
)
