package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ObjectResponse<T>(
    @SerialName("data") val data: T,
)
