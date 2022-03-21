package ru.eruditeonline.app.data.model.test

sealed class Question(
    /** Идентификатор вопроса */
    val id: Int,
    /** Вопрос */
    val text: String,
    /** Ссылка на изображение */
    val image: String?,
    /** Тип вопроса */
    val type: QuestionType,
) {
    class ListAnswer(
        id: Int,
        text: String,
        image: String?,
        /** Варианты ответов */
        val answers: List<Answer>,
    ) : Question(id, text, image, QuestionType.LIST_ANSWER)

    class SingleAnswer(
        id: Int,
        text: String,
        image: String?,
        /** Подпись для поля ответа */
        val label: String,
    ) : Question(id, text, image, QuestionType.SINGLE_ANSWER)
}
