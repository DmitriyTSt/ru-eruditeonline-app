package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.auth.ApiToken

@Serializable
data class TokenData(
    @SerialName("token") val token: ApiToken? = null,
)
