package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListResponse<T>(
    @SerialName("data") val data: Data<T>? = null,
) {
    @Serializable
    data class Data<T>(
        @SerialName("list") val list: List<T>? = null,
        @SerialName("hasMore") val hasMore: Boolean? = null,
    )
}
