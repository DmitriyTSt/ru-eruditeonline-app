package ru.eruditeonline.network.di

import ru.eruditeonline.network.domain.repository.TokenRepository
import ru.eruditeonline.network.domain.usecase.ClearSessionUseCase
import ru.eruditeonline.network.domain.usecase.RefreshTokenUseCase

interface NetworkDeps {
    fun tokenRepository(): TokenRepository
    fun refreshTokenUseCase(): RefreshTokenUseCase
    fun clearSessionUseCase(): ClearSessionUseCase
}