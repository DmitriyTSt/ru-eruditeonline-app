package ru.eruditeonline.app.data.remote.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiDevice(
    @SerialName("id") val id: String,
    @SerialName("os") val os: String = "android",
)
