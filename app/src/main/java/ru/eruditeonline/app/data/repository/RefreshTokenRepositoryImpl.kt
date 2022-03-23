package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.AuthMapper
import ru.eruditeonline.app.data.model.auth.Token
import ru.eruditeonline.app.data.remote.RefreshApiService
import ru.eruditeonline.app.data.remote.params.RefreshTokenParams
import javax.inject.Inject

class RefreshTokenRepositoryImpl @Inject constructor(
    private val apiService: RefreshApiService,
    private val authMapper: AuthMapper,
) : RefreshTokenRepository {
    override suspend fun refreshToken(deviceId: String, refreshToken: String): Token {
        return apiService.refreshToken(RefreshTokenParams(deviceId, refreshToken))
            .data
            .token
            .let { authMapper.fromApiToModel(it) }
    }
}
