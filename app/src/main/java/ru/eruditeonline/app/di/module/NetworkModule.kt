package ru.eruditeonline.app.di.module

import dagger.Binds
import dagger.Module
import ru.eruditeonline.app.data.repository.TokenRepositoryImpl
import ru.eruditeonline.app.domain.usecase.ClearSessionUseCaseImpl
import ru.eruditeonline.app.domain.usecase.auth.RefreshTokenUseCaseImpl
import ru.eruditeonline.network.di.module.NetworkModuleProvider
import ru.eruditeonline.network.domain.repository.TokenRepository
import ru.eruditeonline.network.domain.usecase.ClearSessionUseCase
import ru.eruditeonline.network.domain.usecase.RefreshTokenUseCase

@Module
abstract class NetworkModule : NetworkModuleProvider() {

    @Binds
    abstract fun provideTokenRepository(tokenRepository: TokenRepositoryImpl): TokenRepository

    @Binds
    abstract fun provideRefreshTokenUseCase(refreshTokenUseCase: RefreshTokenUseCaseImpl): RefreshTokenUseCase

    @Binds
    abstract fun provideClearSessionUseCase(clearSessionUseCase: ClearSessionUseCaseImpl): ClearSessionUseCase
}
