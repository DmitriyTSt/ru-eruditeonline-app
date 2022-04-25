package ru.eruditeonline.app.data.remote.model.profile

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.base.ApiCountry

class ApiProfile(
    @SerializedName("id") val id: Int?,
    @SerializedName("email") val email: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("surname") val surname: String?,
    @SerializedName("patronymic") val patronymic: String?,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("country") val country: ApiCountry?,
    @SerializedName("region") val region: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("school") val school: String?,
)
