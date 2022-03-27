package ru.eruditeonline.app.data.model.test

class ResultAnswer(
    /** Вопрос */
    val question: Question,
    /** Текст ответа */
    val answerText: String,
    /** Правильность */
    val correct: Correction?,
) {
    class Question(
        /** Заголовок */
        val title: String,
        /** Текст вопроса */
        val text: String,
    )

    class Correction(
        /** Текст правильного ответа */
        val answerText: String,
        /** Правильно ли ответил */
        val isCorrect: Boolean,
    )
}
