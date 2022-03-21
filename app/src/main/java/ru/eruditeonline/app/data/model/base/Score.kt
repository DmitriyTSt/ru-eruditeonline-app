package ru.eruditeonline.app.data.model.base

import androidx.annotation.ColorInt

/**
 * Результат
 */
class Score(
    /** Набранные баллы */
    val current: Int,
    /** Из скольки */
    val max: Int,
    /** Цвет */
    @ColorInt val color: Int?,
)
