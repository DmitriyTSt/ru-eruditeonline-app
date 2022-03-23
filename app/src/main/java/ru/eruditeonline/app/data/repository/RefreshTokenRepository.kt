package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.auth.Token

interface RefreshTokenRepository {
    /** Рефреш токена */
    suspend fun refreshToken(deviceId: String, refreshToken: String): Token
}
