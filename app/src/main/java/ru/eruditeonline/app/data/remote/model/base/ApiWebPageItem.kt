package ru.eruditeonline.app.data.remote.model.base

import com.google.gson.annotations.SerializedName

class ApiWebPageItem(
    @SerializedName("path") val path: String?,
    @SerializedName("name") val name: String?,
)