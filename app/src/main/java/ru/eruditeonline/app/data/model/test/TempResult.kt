package ru.eruditeonline.app.data.model.test

import ru.eruditeonline.app.data.model.base.Score

class TempResult(
    /** Временный идентификатор прохождения теста */
    val id: String,
    /** Выбранные ответы */
    val answers: List<TempResultAnswer>,
    /** Набранные баллы */
    val score: Score,
    /** Затраченное время, в секундах */
    val spentTime: Long,
    /** Данные результата (null если олимпиада) */
    val resultInfo: ResultInfo?,
)
