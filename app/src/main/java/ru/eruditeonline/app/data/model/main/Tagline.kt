package ru.eruditeonline.app.data.model.main

import androidx.annotation.ColorInt

class Tagline(
    /** Заголовок */
    val title: String,
    /** Текст */
    val text: String,
    /** Ссылка на иконку */
    val icon: String,
    /** Цвет заголовка */
    @ColorInt val titleColor: Int?,
)
