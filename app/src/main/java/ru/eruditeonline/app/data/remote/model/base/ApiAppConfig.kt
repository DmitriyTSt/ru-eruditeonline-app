package ru.eruditeonline.app.data.remote.model.base

import com.google.gson.annotations.SerializedName

class ApiAppConfig(
    @SerializedName("appUpdate") val appUpdate: ApiAppUpdate?,
)
