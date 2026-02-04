package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.base.ApiWebPage

@Serializable
data class WebPageResponse(
    @SerialName("page") val page: ApiWebPage? = null,
)
