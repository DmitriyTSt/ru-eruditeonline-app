package ru.eruditeonline.app.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.eruditeonline.app.data.remote.ApiService
import ru.eruditeonline.app.data.remote.RefreshApiService
import ru.eruditeonline.network.di.util.RefreshTokenClient
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit,
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRefreshApiService(
        @RefreshTokenClient retrofit: Retrofit,
    ): RefreshApiService {
        return retrofit.create(RefreshApiService::class.java)
    }
}
