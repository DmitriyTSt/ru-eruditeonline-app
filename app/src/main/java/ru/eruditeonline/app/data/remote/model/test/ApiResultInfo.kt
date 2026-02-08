package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResultInfo(
    /** Место (Победитель (2 место)) */
    @SerialName("placeText") val placeText: String? = null,
    /** Средний балл по вем участникам в процентах */
    @SerialName("averageScore") val averageScore: Int? = null,
    /** Описание результата */
    @SerialName("resultText") val resultText: String? = null,
)
