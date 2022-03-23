package ru.eruditeonline.app.data.remote.model.base

import com.google.gson.annotations.SerializedName

class ApiCountry(
    @SerializedName("name") val name: String?,
    @SerializedName("code") val code: String?,
    @SerializedName("image") val image: String?,
)
