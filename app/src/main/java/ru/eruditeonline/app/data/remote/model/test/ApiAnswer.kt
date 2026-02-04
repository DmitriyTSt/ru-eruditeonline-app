package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiAnswer(
    /** Идентификатор ответа */
    @SerialName("id") val id: String? = null,
    /** Ответ */
    @SerialName("text") val text: String? = null,
    /** Изображение ответа */
    @SerialName("image") val image: String? = null,
)
