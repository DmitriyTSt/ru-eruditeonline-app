package ru.eruditeonline.app.data.remote.response

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.auth.ApiToken

class TokenData(
    @SerializedName("token") val token: ApiToken?,
)
