package ru.eruditeonline.app.data.remote.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiToken(
    @SerialName("accessToken") val accessToken: String? = null,
    @SerialName("refreshToken") val refreshToken: String? = null,
    @SerialName("expiresIn") val expiresIn: Long? = null,
)
