package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName

class ApiResultAnswer(
    /** Вопрос */
    @SerializedName("question") val question: Question?,
    /** Текст ответа */
    @SerializedName("answerText") val answerText: String?,
    /** Правильность */
    @SerializedName("correct") val correct: Correction?,
) {
    class Question(
        /** Заголовок */
        @SerializedName("title") val title: String?,
        /** Текст вопроса */
        @SerializedName("text") val text: String?,
    )

    class Correction(
        /** Текст правильного ответа */
        @SerializedName("answerText") val answerText: String?,
        /** Правильно ли ответил */
        @SerializedName("isCorrect") val isCorrect: Boolean?,
    )
}
