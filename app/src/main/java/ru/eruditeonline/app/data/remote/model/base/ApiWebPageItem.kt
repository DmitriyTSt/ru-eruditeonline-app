package ru.eruditeonline.app.data.remote.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiWebPageItem(
    @SerialName("path") val path: String? = null,
    @SerialName("name") val name: String? = null,
)
