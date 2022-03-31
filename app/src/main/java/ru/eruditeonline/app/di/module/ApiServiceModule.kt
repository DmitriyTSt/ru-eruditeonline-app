package ru.eruditeonline.app.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.eruditeonline.app.data.mapper.EnumConverterFactory
import ru.eruditeonline.app.data.remote.ApiService
import ru.eruditeonline.app.data.remote.RefreshApiService
import ru.eruditeonline.app.data.remote.interceptor.TokenAuthenticator
import ru.eruditeonline.app.data.repository.AppInfoRepository
import ru.eruditeonline.app.data.repository.EndpointRepository
import ru.eruditeonline.app.data.repository.TokenRepository
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

private const val HEADER_USER_AGENT = "User-Agent"
private const val HEADER_CONTENT_TYPE = "Content-Type"
private const val CONNECTION_TIMEOUTS_MS = 20000L

@Module
class ApiServiceModule {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    /**
     * В authenticator OkHttpClient нужно рефрешнуть токен. Для этого нужен репозиторий, который вызовет запрос.
     * Но если используется один OkHttpClient, получим циклическую зависимость.
     * Так что создаем дополнительный OkHttpClient, apiService и репозиторий
     */
    @Qualifier
    annotation class RefreshTokenClient

    @Provides
    @Singleton
    fun provideRefreshTokenLock(): RefreshTokenLock {
        return RefreshTokenLock()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Context,
        appInfoRepository: AppInfoRepository,
        tokenRepository: TokenRepository,
        tokenAuthenticator: TokenAuthenticator,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            setTimeouts()
            addMetadataInterceptor(appInfoRepository)
            addAuthInterceptor(tokenRepository)
            addRefreshTokenAuthenticator(tokenAuthenticator)

            // логгер запросов в шторке для тестировния
            if (!appInfoRepository.isRelease) {
                addInterceptor(ChuckerInterceptor.Builder(context).build())
            }
        }.build()
    }

    @Provides
    @Singleton
    @RefreshTokenClient
    fun provideRefreshTokenOkHttpClient(
        context: Context,
        appInfoRepository: AppInfoRepository,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            setTimeouts()
            addMetadataInterceptor(appInfoRepository)

            // логгер запросов в шторке для тестировния
            if (!appInfoRepository.isRelease) {
                addInterceptor(ChuckerInterceptor.Builder(context).build())
            }
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        endpointRepository: EndpointRepository,
        gson: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(endpointRepository.provideEndpoint())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumConverterFactory())
            .build()
    }

    @Singleton
    @Provides
    @RefreshTokenClient
    fun provideRefreshTokenRetrofit(
        endpointRepository: EndpointRepository,
        gson: Gson,
        @RefreshTokenClient client: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(endpointRepository.provideEndpoint())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumConverterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideRefreshApiService(
        @RefreshTokenClient retrofit: Retrofit
    ): RefreshApiService {
        return retrofit.create(RefreshApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    private fun OkHttpClient.Builder.setTimeouts() {
        connectTimeout(CONNECTION_TIMEOUTS_MS, TimeUnit.MILLISECONDS)
        readTimeout(CONNECTION_TIMEOUTS_MS, TimeUnit.MILLISECONDS)
        writeTimeout(CONNECTION_TIMEOUTS_MS, TimeUnit.MILLISECONDS)
    }

    private fun OkHttpClient.Builder.addMetadataInterceptor(
        appInfoRepository: AppInfoRepository,
    ) {
        addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header(HEADER_USER_AGENT, appInfoRepository.userAgent)
                .header(HEADER_CONTENT_TYPE, "application/json")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
    }

    private fun OkHttpClient.Builder.addAuthInterceptor(
        tokenRepository: TokenRepository,
    ) {
        addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .apply {
                    val token = tokenRepository.accessToken
                    if (!token.isNullOrEmpty()) {
                        header(HEADER_AUTHORIZATION, token)
                    }
                }
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
    }

    private fun OkHttpClient.Builder.addRefreshTokenAuthenticator(
        tokenAuthenticator: TokenAuthenticator,
    ) {
        // Перехватываем 401 ошибку
        authenticator(tokenAuthenticator)
    }
}

class RefreshTokenLock
