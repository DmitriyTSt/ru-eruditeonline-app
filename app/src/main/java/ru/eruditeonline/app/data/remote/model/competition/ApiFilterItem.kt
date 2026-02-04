package ru.eruditeonline.app.data.remote.model.competition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Элемент фильтра
 */
@Serializable
data class ApiFilterItem(
    /** Идентификатор */
    @SerialName("id") val id: String? = null,
    /** Название */
    @SerialName("title") val title: String? = null,
    /** Выбран ли фильтр */
    @SerialName("selected") val selected: Boolean? = null,
)
