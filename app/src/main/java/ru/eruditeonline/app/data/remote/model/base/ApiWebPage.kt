package ru.eruditeonline.app.data.remote.model.base

import com.google.gson.annotations.SerializedName

class ApiWebPage(
    @SerializedName("title") val title: String?,
    @SerializedName("content") val content: String?,
)