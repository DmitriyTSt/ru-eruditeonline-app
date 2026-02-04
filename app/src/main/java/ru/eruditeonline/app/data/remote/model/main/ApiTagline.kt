package ru.eruditeonline.app.data.remote.model.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiTagline(
    /** Заголовок */
    @SerialName("title") val title: String? = null,
    /** Текст */
    @SerialName("text") val text: String? = null,
    /** Ссылка на иконку */
    @SerialName("icon") val icon: String? = null,
    /** Цвет заголовка */
    @SerialName("titleColor") val titleColor: String? = null,
    /** Диплинк */
    @SerialName("url") val url: String? = null,
)
