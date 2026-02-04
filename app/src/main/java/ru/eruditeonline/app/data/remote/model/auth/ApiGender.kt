package ru.eruditeonline.app.data.remote.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiGender {
    @SerialName("NOT_SET") NOT_SET,
    @SerialName("MALE") MALE,
    @SerialName("FEMALE") FEMALE,
}
