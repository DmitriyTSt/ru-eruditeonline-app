package ru.eruditeonline.app.di.module

import dagger.Binds
import dagger.Module
import ru.eruditeonline.app.data.repository.AppInfoRepository
import ru.eruditeonline.app.data.repository.AppInfoRepositoryImpl
import ru.eruditeonline.app.data.repository.AuthRepository
import ru.eruditeonline.app.data.repository.AuthRepositoryImpl
import ru.eruditeonline.app.data.repository.BuildConfigRepository
import ru.eruditeonline.app.data.repository.BuildConfigRepositoryImpl
import ru.eruditeonline.app.data.repository.CompetitionRepository
import ru.eruditeonline.app.data.repository.CompetitionRepositoryImpl
import ru.eruditeonline.app.data.repository.EndpointRepository
import ru.eruditeonline.app.data.repository.EndpointRepositoryImpl
import ru.eruditeonline.app.data.repository.MainRepository
import ru.eruditeonline.app.data.repository.MainRepositoryImpl
import ru.eruditeonline.app.data.repository.ProfileRepository
import ru.eruditeonline.app.data.repository.ProfileRepositoryImpl
import ru.eruditeonline.app.data.repository.RatingRepository
import ru.eruditeonline.app.data.repository.RatingRepositoryImpl
import ru.eruditeonline.app.data.repository.RefreshTokenRepository
import ru.eruditeonline.app.data.repository.RefreshTokenRepositoryImpl
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.app.data.repository.ResultRepositoryImpl
import ru.eruditeonline.app.data.repository.TestRepository
import ru.eruditeonline.app.data.repository.TestRepositoryImpl
import ru.eruditeonline.app.data.repository.TokenRepository
import ru.eruditeonline.app.data.repository.TokenRepositoryImpl
import ru.eruditeonline.app.data.repository.UtilsRepository
import ru.eruditeonline.app.data.repository.UtilsRepositoryImpl

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun provideRefreshTokenRepository(refreshTokenRepositoryImpl: RefreshTokenRepositoryImpl): RefreshTokenRepository

    @Binds
    abstract fun provideTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository

    @Binds
    abstract fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun provideRatingRepository(ratingRepositoryImpl: RatingRepositoryImpl): RatingRepository

    @Binds
    abstract fun provideResultRepository(resultRepositoryImpl: ResultRepositoryImpl): ResultRepository

    @Binds
    abstract fun provideCompetitionRepository(competitionRepositoryImpl: CompetitionRepositoryImpl): CompetitionRepository

    @Binds
    abstract fun provideTestRepository(testRepositoryImpl: TestRepositoryImpl): TestRepository

    @Binds
    abstract fun provideUtilsRepository(utilsRepositoryImpl: UtilsRepositoryImpl): UtilsRepository

    @Binds
    abstract fun provideMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun provideBuildConfigRepository(buildConfigRepositoryImpl: BuildConfigRepositoryImpl): BuildConfigRepository

    @Binds
    abstract fun provideAppInfoRepository(appInfoRepositoryImpl: AppInfoRepositoryImpl): AppInfoRepository

    @Binds
    abstract fun provideEndpointRepository(endpointRepositoryImpl: EndpointRepositoryImpl): EndpointRepository
}
