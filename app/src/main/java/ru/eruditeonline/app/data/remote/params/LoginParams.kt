package ru.eruditeonline.app.data.remote.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginParams(
    @SerialName("login") val login: String,
    @SerialName("password") val password: String,
)
