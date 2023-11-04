package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.test.CreatedResult
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.data.model.test.TestUserResult
import ru.eruditeonline.app.data.model.test.TestUserResultRow

interface ResultRepository {
    /** Получение результатов пользователя */
    suspend fun getUserResults(query: String?, offset: Int, limit: Int): List<TestUserResultRow>

    /** Получение результатов по email */
    suspend fun getResultsByEmail(email: String, offset: Int, limit: Int): List<TestUserResultRow>

    /** Получение общих результатов */
    suspend fun getCommonResults(offset: Int, limit: Int): List<TestCommonResultRow>

    /** Получение результата */
    suspend fun getResult(id: Int): TestUserResult

    /** Сохранение результата */
    suspend fun saveResult(
        completeId: Int,
        name: String,
        surname: String,
        patronymic: String?,
        school: String?,
        position: String?,
        teacher: String?,
        country: String,
        city: String,
        region: String?,
        email: String,
        teacherEmail: String?,
        diplomaType: String,
        quality: Int?,
        difficulty: Int?,
        interest: Int?,
    ): CreatedResult
}
