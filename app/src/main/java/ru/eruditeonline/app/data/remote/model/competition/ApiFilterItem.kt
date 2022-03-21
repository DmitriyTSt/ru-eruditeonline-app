package ru.eruditeonline.app.data.remote.model.competition

import com.google.gson.annotations.SerializedName

/**
 * Элемент фильтра
 */
class ApiFilterItem(
    /** Идентификатор */
    @SerializedName("id") val id: String?,
    /** Название */
    @SerializedName("title") val title: String?,
    /** Выбран ли фильтр */
    @SerializedName("selected") val selected: Boolean?,
)
