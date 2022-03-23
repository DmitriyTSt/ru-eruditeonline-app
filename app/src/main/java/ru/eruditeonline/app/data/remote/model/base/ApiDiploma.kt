package ru.eruditeonline.app.data.remote.model.base

import com.google.gson.annotations.SerializedName

class ApiDiploma(
    @SerializedName("type") val type: String?,
    @SerializedName("image") val image: String?,
)
