package ru.eruditeonline.app.data.remote.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAppConfigParams(
    @SerialName("appVersion") val appVersion: String,
)
