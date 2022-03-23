package ru.eruditeonline.app.data.model.competition

import androidx.annotation.ColorInt

class TestAgeGroup(
    /** Идентификатор теста */
    val id: String,
    /** Наименование */
    val title: String,
    /** Цвет фона */
    @ColorInt val color: Int?,
    /** Ссылка на иконку */
    val icon: String?,
)
