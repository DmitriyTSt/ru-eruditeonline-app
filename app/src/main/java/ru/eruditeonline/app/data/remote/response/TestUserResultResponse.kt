package ru.eruditeonline.app.data.remote.response

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.test.ApiTestUserResult

class TestUserResultResponse(
    @SerializedName("result") val result: ApiTestUserResult?,
)
