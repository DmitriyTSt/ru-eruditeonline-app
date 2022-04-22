package ru.eruditeonline.app.data.model.base

data class WebPage(
    /** Заголовок страницы */
    val title: String,
    /** Контент страницы */
    val content: String,
    /** Путь страницы */
    val path: String = "",
)
