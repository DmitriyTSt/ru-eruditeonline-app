package ru.eruditeonline.app.data.model.test

import ru.eruditeonline.app.data.model.base.Score
import java.time.LocalDate

class TestUserResult(
    /** Идентификатор результата */
    val id: Int,
    /** Дата прохождения */
    val date: LocalDate,
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
