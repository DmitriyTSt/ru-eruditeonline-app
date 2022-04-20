package ru.eruditeonline.app.data.remote.response

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.profile.ApiProfile

class ProfileData(
    @SerializedName("user") val profile: ApiProfile?,
)
