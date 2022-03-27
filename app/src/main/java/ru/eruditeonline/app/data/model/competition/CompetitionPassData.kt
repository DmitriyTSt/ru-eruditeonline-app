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
        val questionId: String,
    ) {
        class ListAnswer(
            questionId: String,
            val answerId: String,
        ) : Question(questionId)

        class SingleAnswer(
            questionId: String,
            val textAnswer: String,
        ) : Question(questionId)
    }
}
