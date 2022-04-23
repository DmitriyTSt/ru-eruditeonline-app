package ru.eruditeonline.app.data.model.test

import ru.eruditeonline.app.data.model.Similarable
import ru.eruditeonline.app.data.model.base.Score
import java.time.LocalDate

data class TestUserResultRow(
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
) : Similarable<TestUserResultRow> {
    override fun areItemsTheSame(other: TestUserResultRow): Boolean {
        return this.id == other.id
    }

    override fun areContentsTheSame(other: TestUserResultRow): Boolean {
        return this == other
    }
}
