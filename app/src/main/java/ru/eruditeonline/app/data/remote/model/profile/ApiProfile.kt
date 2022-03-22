package ru.eruditeonline.app.data.remote.model.profile

import com.google.gson.annotations.SerializedName

class ApiProfile(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("surname") val surname: String?,
    @SerializedName("patronymic") val patronymic: String?,
    @SerializedName("avatar") val avatar: String?,
)
