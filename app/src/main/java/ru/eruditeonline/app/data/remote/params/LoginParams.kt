package ru.eruditeonline.app.data.remote.params

import com.google.gson.annotations.SerializedName

class LoginParams(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String,
)
