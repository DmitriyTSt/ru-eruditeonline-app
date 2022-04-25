package ru.eruditeonline.app.data.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error") val error: Data?
) {
    data class Data(
        @SerializedName("code") val code: String?,
        @SerializedName("message") val message: String?,
    )
}
