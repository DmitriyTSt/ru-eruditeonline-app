package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName

class ApiAnswer(
    /** Идентификатор ответа */
    @SerializedName("id") val id: String?,
    /** Ответ */
    @SerializedName("text") val text: String?,
    /** Изображение ответа */
    @SerializedName("image") val image: String?,
)
