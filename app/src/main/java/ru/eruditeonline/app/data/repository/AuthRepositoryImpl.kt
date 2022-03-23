package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.AuthMapper
import ru.eruditeonline.app.data.model.auth.Gender
import ru.eruditeonline.app.data.model.auth.Token
import ru.eruditeonline.app.data.remote.ApiService
import ru.eruditeonline.app.data.remote.model.base.ApiDevice
import ru.eruditeonline.app.data.remote.params.CreateAnonymParams
import ru.eruditeonline.app.data.remote.params.LoginParams
import ru.eruditeonline.app.data.remote.params.RegistrationParams
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val authMapper: AuthMapper,
) : AuthRepository {
    override suspend fun createAnonym(deviceId: String): Token {
        return apiService.createAnonym(CreateAnonymParams(ApiDevice(deviceId)))
            .data
            .token
            .let { authMapper.fromApiToModel(it) }
    }

    override suspend fun confirmEmail(token: String) {
        apiService.confirmEmail(token)
    }

    override suspend fun login(login: String, password: String): Token {
        return apiService.login(LoginParams(login, password))
            .data
            .token
            .let { authMapper.fromApiToModel(it) }
    }

    override suspend fun logout(): Token {
        return apiService.logout()
            .data
            .token
            .let { authMapper.fromApiToModel(it) }
    }

    override suspend fun registration(
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
        emailAgreement: Boolean
    ) {
        apiService.registration(
            RegistrationParams(
                email = email,
                password = password,
                name = name,
                surname = surname,
                patronymic = patronymic,
                birthday = birthday,
                gender = authMapper.fromModelToApi(gender),
                company = company,
                city = city,
                region = region,
                country = country,
                emailAgreement = emailAgreement
            )
        )
    }
}
