package ru.eruditeonline.app.data.remote.params

import com.google.gson.annotations.SerializedName

class RefreshTokenParams(
    @SerializedName("deviceId") val deviceId: String,
    @SerializedName("refreshToken") val refreshToken: String,
)
