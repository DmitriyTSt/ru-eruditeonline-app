package ru.eruditeonline.app.data.remote.params

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.auth.ApiGender

class RegistrationParams(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("patronymic") val patronymic: String?,
    @SerializedName("birthday") val birthday: Long?,
    @SerializedName("gender") val gender: ApiGender,
    @SerializedName("company") val company: String?,
    @SerializedName("city") val city: String,
    @SerializedName("region") val region: String?,
    @SerializedName("country") val country: String,
    @SerializedName("emailAgreement") val emailAgreement: Boolean,
)
