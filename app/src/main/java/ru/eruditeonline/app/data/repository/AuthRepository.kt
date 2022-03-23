package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.auth.Gender
import ru.eruditeonline.app.data.model.auth.Token

interface AuthRepository {
    /** Создание анонимного пользователя */
    suspend fun createAnonym(deviceId: String): Token

    /** Подтверждение почты */
    suspend fun confirmEmail(token: String)

    /** Авторизация */
    suspend fun login(login: String, password: String): Token

    /** Выход */
    suspend fun logout(): Token

    /** Регистрация */
    suspend fun registration(
        email: String,
        password: String,
        name: String,
        surname: String,
        patronymic: String?,
        birthday: Long?,
        gender: Gender,
        company: String?,
        city: String,
        region: String?,
        country: String,
        emailAgreement: Boolean,
    )
}
