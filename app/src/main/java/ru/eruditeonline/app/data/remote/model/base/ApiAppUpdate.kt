package ru.eruditeonline.app.data.remote.model.base

import com.google.gson.annotations.SerializedName

class ApiAppUpdate(
    @SerializedName("forceUpdate") val forceUpdate: Boolean?,
)
