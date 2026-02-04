package ru.eruditeonline.app.data.remote.model.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.base.ApiCountry

@Serializable
data class ApiProfile(
    @SerialName("id") val id: Int? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("surname") val surname: String? = null,
    @SerialName("patronymic") val patronymic: String? = null,
    @SerialName("avatar") val avatar: String? = null,
    @SerialName("country") val country: ApiCountry? = null,
    @SerialName("region") val region: String? = null,
    @SerialName("city") val city: String? = null,
    @SerialName("school") val school: String? = null,
)
