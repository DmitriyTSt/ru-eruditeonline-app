package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName

class ApiCreatedResult(
    /** Идентификатор результата */
    @SerializedName("id") val id: String?,
    /** Участник */
    @SerializedName("username") val username: String?,
    /** Постоянная ссылка на результат прохождения */
    @SerializedName("resultLink") val resultLink: String?,
    @SerializedName("achievementText") val achievementText: String?,
)
