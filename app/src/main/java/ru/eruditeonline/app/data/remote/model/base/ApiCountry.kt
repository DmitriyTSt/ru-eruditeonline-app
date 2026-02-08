package ru.eruditeonline.app.data.remote.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCountry(
    @SerialName("name") val name: String? = null,
    @SerialName("code") val code: String? = null,
    @SerialName("image") val image: String? = null,
)
