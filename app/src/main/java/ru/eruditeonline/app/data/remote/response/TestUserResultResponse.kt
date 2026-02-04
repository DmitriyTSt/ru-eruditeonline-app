package ru.eruditeonline.app.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.test.ApiTestUserResult

@Serializable
data class TestUserResultResponse(
    @SerialName("result") val result: ApiTestUserResult? = null,
)
