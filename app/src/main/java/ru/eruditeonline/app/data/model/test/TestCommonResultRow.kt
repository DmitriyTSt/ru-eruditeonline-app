package ru.eruditeonline.app.data.model.test

import ru.eruditeonline.app.data.model.Similarable

data class TestCommonResultRow(
    /** Дата прохождения */
    val date: String,
    /** Участник */
    val username: String,
    /** Регион (населенный пункт) */
    val city: String,
    /** Ссылка на иконку страны */
    val countryIcon: String,
    /** Идентификатор конкурса */
    val competitionId: String,
    /** Конкурс */
    val competitionTitle: String,
    /** Текст результата */
    val resultText: String,
) : Similarable<TestCommonResultRow> {
    override fun areItemsTheSame(other: TestCommonResultRow): Boolean {
        return false
    }

    override fun areContentsTheSame(other: TestCommonResultRow): Boolean {
        return this == other
    }
}
