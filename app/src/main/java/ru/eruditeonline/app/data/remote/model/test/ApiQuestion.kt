package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiQuestion(
    /** Идентификатор вопроса */
    @SerialName("id") val id: Int? = null,
    /** Вопрос */
    @SerialName("text") val text: String? = null,
    /** Ссылка на изображение */
    @SerialName("image") val image: String? = null,
    /** Тип вопроса */
    @SerialName("type") val type: ApiQuestionType? = null,
    /** Варианты ответов (для LIST_ANSWER) */
    @SerialName("answers") val answers: List<ApiAnswer>? = null,
    /** Подпись для поля ответа (для SINGLE_ANSWER) */
    @SerialName("label") val label: String? = null,
)
