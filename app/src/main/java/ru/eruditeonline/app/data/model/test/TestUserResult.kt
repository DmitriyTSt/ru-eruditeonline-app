package ru.eruditeonline.app.data.model.test

import ru.eruditeonline.app.data.model.base.Score

class TestUserResult(
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
    val place: String,
    /** Балл */
    val score: Score,
    /** Затраченное время */
    val spentTime: Long,
    /** Ответы */
    val answers: List<ResultAnswer>,
)
