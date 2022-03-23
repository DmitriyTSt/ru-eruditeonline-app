package ru.eruditeonline.app.data.repository

import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor() : TokenRepository {
    override var accessToken: String?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var refreshToken: String?
        get() = TODO("Not yet implemented")
        set(value) {}
}
