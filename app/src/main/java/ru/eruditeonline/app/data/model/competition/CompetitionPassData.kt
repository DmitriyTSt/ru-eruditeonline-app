package ru.eruditeonline.app.data.model.competition

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CompetitionPassData(
    /** Идентификатор теста */
    val testId: String,
    /** Ответы на вопросы */
    val questionResults: List<Question>,
    /** Потраченное на прохождение время в секундах */
    val spentTime: Long,
) : Parcelable {
    sealed class Question(
        open val questionId: Int,
    ) : Parcelable {
        @Parcelize
        class ListAnswer(
            override val questionId: Int,
            val answerId: String,
        ) : Question(questionId)

        @Parcelize
        class SingleAnswer(
            override val questionId: Int,
            val textAnswer: String,
        ) : Question(questionId)
    }
}
