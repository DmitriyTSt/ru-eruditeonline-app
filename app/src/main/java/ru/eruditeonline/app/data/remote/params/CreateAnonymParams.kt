package ru.eruditeonline.app.data.remote.params

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.base.ApiDevice

class CreateAnonymParams(
    @SerializedName("device") val device: ApiDevice,
)
