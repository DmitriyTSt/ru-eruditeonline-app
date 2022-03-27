package ru.eruditeonline.app.data.mapper

import ru.eruditeonline.app.data.model.auth.Gender
import ru.eruditeonline.app.data.model.auth.Token
import ru.eruditeonline.app.data.remote.model.auth.ApiGender
import ru.eruditeonline.app.data.remote.model.auth.ApiToken
import javax.inject.Inject

class AuthMapper @Inject constructor() {
    fun fromApiToModel(token: ApiToken?): Token {
        return Token(
            accessToken = token?.accessToken ?: throwWrongData(),
            refreshToken = token.refreshToken ?: throwWrongData(),
            expiresIn = token.expiresIn ?: throwWrongData(),
        )
    }

    fun fromModelToApi(model: Gender): ApiGender {
        return when (model) {
            Gender.NOT_SET -> ApiGender.NOT_SET
            Gender.MALE -> ApiGender.MALE
            Gender.FEMALE -> ApiGender.FEMALE
        }
    }

    private fun throwWrongData(): Nothing {
        throw IllegalStateException("Wrong token data from api")
    }
}
