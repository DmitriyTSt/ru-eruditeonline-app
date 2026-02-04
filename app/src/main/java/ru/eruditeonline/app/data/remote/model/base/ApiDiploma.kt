package ru.eruditeonline.app.data.remote.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiDiploma(
    @SerialName("type") val type: String? = null,
    @SerialName("image") val image: String? = null,
)
