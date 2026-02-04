package ru.eruditeonline.app.data.remote.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingParams(
    @SerialName("day") val day: Int? = null,
    @SerialName("month") val month: Int? = null,
    @SerialName("year") val year: Int,
)
