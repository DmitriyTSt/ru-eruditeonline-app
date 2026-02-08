package ru.eruditeonline.app.data.remote.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiAppUpdate(
    @SerialName("forceUpdate") val forceUpdate: Boolean? = null,
)
