package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResultAnswer(
    /** Вопрос */
    @SerialName("question") val question: Question? = null,
    /** Текст ответа */
    @SerialName("answerText") val answerText: String? = null,
    /** Правильность */
    @SerialName("correct") val correct: Correction? = null,
) {
    @Serializable
    data class Question(
        /** Заголовок */
        @SerialName("title") val title: String? = null,
        /** Текст вопроса */
        @SerialName("text") val text: String? = null,
    )

    @Serializable
    data class Correction(
        /** Текст правильного ответа */
        @SerialName("answerText") val answerText: String? = null,
        /** Правильно ли ответил */
        @SerialName("isCorrect") val isCorrect: Boolean? = null,
    )
}
