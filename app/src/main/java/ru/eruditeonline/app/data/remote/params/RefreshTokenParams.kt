package ru.eruditeonline.app.data.remote.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenParams(
    @SerialName("deviceId") val deviceId: String,
    @SerialName("refreshToken") val refreshToken: String,
)
