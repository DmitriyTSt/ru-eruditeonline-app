package ru.eruditeonline.app.data.remote.response

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.base.ApiWebPage

class WebPageResponse(
    @SerializedName("page") val page: ApiWebPage?,
)
