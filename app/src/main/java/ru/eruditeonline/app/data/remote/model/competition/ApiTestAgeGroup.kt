package ru.eruditeonline.app.data.remote.model.competition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiTestAgeGroup(
    /** Идентификатор теста */
    @SerialName("id") val id: String? = null,
    /** Наименование */
    @SerialName("title") val title: String? = null,
    /** Цвет фона */
    @SerialName("color") val color: String? = null,
    /** Ссылка на иконку */
    @SerialName("icon") val icon: String? = null,
)
