package ru.eruditeonline.app.data.remote.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.auth.ApiGender

@Serializable
data class RegistrationParams(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("name") val name: String,
    @SerialName("surname") val surname: String,
    @SerialName("patronymic") val patronymic: String? = null,
    @SerialName("birthday") val birthday: Long? = null,
    @SerialName("gender") val gender: ApiGender,
    @SerialName("company") val company: String? = null,
    @SerialName("city") val city: String,
    @SerialName("region") val region: String? = null,
    @SerialName("country") val country: String,
    @SerialName("emailAgreement") val emailAgreement: Boolean,
)
