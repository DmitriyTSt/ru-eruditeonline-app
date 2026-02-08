package ru.eruditeonline.app.data.remote.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiWebPage(
    @SerialName("title") val title: String? = null,
    @SerialName("content") val content: String? = null,
)
