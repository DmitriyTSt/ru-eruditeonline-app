package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCreatedResult(
    /** Идентификатор результата */
    @SerialName("id") val id: Int? = null,
    /** Участник */
    @SerialName("username") val username: String? = null,
    /** Постоянная ссылка на результат прохождения */
    @SerialName("resultLink") val resultLink: String? = null,
    @SerialName("achievementText") val achievementText: String? = null,
)
