package ru.eruditeonline.app.data.remote.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.base.ApiDevice

@Serializable
data class CreateAnonymParams(
    @SerialName("device") val device: ApiDevice,
)
