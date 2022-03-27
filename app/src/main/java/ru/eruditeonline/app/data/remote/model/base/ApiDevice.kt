package ru.eruditeonline.app.data.remote.model.base

import com.google.gson.annotations.SerializedName

class ApiDevice(
    @SerializedName("id") val id: String,
    @SerializedName("os") val os: String = "android",
)
