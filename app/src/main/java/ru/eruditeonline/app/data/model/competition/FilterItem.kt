package ru.eruditeonline.app.data.model.competition

/**
 * Элемент фильтра
 */
class FilterItem(
    /** Идентификатор */
    val id: String,
    /** Название */
    val title: String,
    /** Выбран ли фильтр */
    val selected: Boolean,
)
