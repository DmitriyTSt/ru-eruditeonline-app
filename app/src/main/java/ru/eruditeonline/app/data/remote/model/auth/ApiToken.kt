package ru.eruditeonline.app.data.remote.model.auth

import com.google.gson.annotations.SerializedName

class ApiToken(
    @SerializedName("accessToken") val accessToken: String?,
    @SerializedName("refreshToken") val refreshToken: String?,
    @SerializedName("expiresIn") val expiresIn: Long?,
)
