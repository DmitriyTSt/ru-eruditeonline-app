package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.profile.ApiProfile

@Serializable
data class ProfileData(
    @SerialName("user") val profile: ApiProfile? = null,
)
