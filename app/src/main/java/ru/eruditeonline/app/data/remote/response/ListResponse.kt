package ru.eruditeonline.app.data.remote.response

import com.google.gson.annotations.SerializedName

class ListResponse<T>(
    @SerializedName("data") val data: Data<T>?,
) {
    class Data<T>(
        @SerializedName("list") val list: List<T?>?,
        @SerializedName("hasMore") val hasMore: Boolean?,
    )
}
