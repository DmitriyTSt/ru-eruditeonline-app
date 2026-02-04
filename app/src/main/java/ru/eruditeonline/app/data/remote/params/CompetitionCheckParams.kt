package ru.eruditeonline.app.data.remote.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompetitionCheckParams(
    /** Идентификатор теста */
    @SerialName("testId") val testId: String,
    /** Ответы на вопросы */
    @SerialName("questionResults") val questionResults: List<Question>,
    /** Потраченное на прохождение время в секундах */
    @SerialName("spentTime") val spentTime: Long,
) {
    @Serializable
    sealed class Question {
        abstract val questionId: Int

        @Serializable
        data class ListAnswer(
            @SerialName("questionId") override val questionId: Int,
            /** null, если не выбрали */
            @SerialName("answerId") val answerId: String? = null,
        ) : Question()

        @Serializable
        data class SingleAnswer(
            @SerialName("questionId") override val questionId: Int,
            /** null, если не ответили */
            @SerialName("textAnswer") val textAnswer: String? = null,
        ) : Question()
    }
}
