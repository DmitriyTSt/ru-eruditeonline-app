package ru.eruditeonline.app.di.module

import dagger.Binds
import dagger.Module
import ru.eruditeonline.app.data.repository.AuthRepository
import ru.eruditeonline.app.data.repository.AuthRepositoryImpl
import ru.eruditeonline.app.data.repository.ProfileRepository
import ru.eruditeonline.app.data.repository.ProfileRepositoryImpl
import ru.eruditeonline.app.data.repository.RatingRepository
import ru.eruditeonline.app.data.repository.RatingRepositoryImpl
import ru.eruditeonline.app.data.repository.RefreshTokenRepository
import ru.eruditeonline.app.data.repository.RefreshTokenRepositoryImpl
import ru.eruditeonline.app.data.repository.TokenRepository

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun provideRefreshTokenRepository(refreshTokenRepositoryImpl: RefreshTokenRepositoryImpl): RefreshTokenRepository

    @Binds
    abstract fun provideTokenRepository(tokenRepositoryImpl: RefreshTokenRepositoryImpl): TokenRepository

    @Binds
    abstract fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun provideRatingRepository(ratingRepositoryImpl: RatingRepositoryImpl): RatingRepository
}
