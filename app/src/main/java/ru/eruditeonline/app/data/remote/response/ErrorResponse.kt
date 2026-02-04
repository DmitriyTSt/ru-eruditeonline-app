package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("error") val error: Data? = null,
) {
    @Serializable
    data class Data(
        @SerialName("code") val code: String? = null,
        @SerialName("message") val message: String? = null,
    )
}
