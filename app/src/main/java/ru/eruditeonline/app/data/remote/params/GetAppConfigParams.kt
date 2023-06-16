package ru.eruditeonline.app.data.remote.params

import com.google.gson.annotations.SerializedName

class GetAppConfigParams(
    @SerializedName("appVersion") val appVersion: String,
)
