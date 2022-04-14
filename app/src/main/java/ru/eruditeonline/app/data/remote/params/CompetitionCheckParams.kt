package ru.eruditeonline.app.data.remote.params

import com.google.gson.annotations.SerializedName

class CompetitionCheckParams(
    /** Идентификатор теста */
    @SerializedName("testId") val testId: String,
    /** Ответы на вопросы */
    @SerializedName("questionResults") val questionResults: List<Question>,
    /** Потраченное на прохождение время в секундах */
    @SerializedName("spentTime") val spentTime: Long,
) {
    sealed class Question(
        @SerializedName("questionId") val questionId: Int,
    ) {
        class ListAnswer(
            questionId: Int,
            /** null, если не выбрали */
            @SerializedName("answerId") val answerId: String?,
        ) : Question(questionId)

        class SingleAnswer(
            questionId: Int,
            /** null, если не ответили */
            @SerializedName("textAnswer") val textAnswer: String?,
        ) : Question(questionId)
    }
}
