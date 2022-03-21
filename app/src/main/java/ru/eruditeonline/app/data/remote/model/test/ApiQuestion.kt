package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName

class ApiQuestion(
    /** Идентификатор вопроса */
    @SerializedName("id") val id: String?,
    /** Вопрос */
    @SerializedName("text") val text: String?,
    /** Ссылка на изображение */
    @SerializedName("image") val image: String?,
    /** Тип вопроса */
    @SerializedName("type") val type: String?,
    /** Варианты ответов (для LIST_ANSWER) */
    @SerializedName("answers") val answers: List<ApiAnswer?>?,
    /** Подпись для поля ответа (для SINGLE_ANSWER) */
    @SerializedName("label") val label: String?,
)
