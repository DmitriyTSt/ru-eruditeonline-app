package ru.eruditeonline.app.data.model.competition

class CompetitionPassData(
    /** Идентификатор теста */
    val testId: String,
    /** Ответы на вопросы */
    val questionResults: List<Question>,
    /** Потраченное на прохождение время в секундах */
    val spentTime: Long,
) {
    sealed class Question(
        val questionId: Int,
    ) {
        class ListAnswer(
            questionId: Int,
            /** null, если не выбран */
            val answerId: String?,
        ) : Question(questionId)

        class SingleAnswer(
            questionId: Int,
            /** null, если не ответили */
            val textAnswer: String?,
        ) : Question(questionId)
    }
}
