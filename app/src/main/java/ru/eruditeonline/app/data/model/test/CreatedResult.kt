package ru.eruditeonline.app.data.model.test

class CreatedResult(
    /** Идентификатор результата */
    val id: String,
    /** Участник */
    val username: String,
    /** Постоянная ссылка на результат прохождения */
    val resultLink: String,
    val achievementText: String?,
)
