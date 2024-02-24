package ru.eruditeonline.network.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.eruditeonline.network.data.interceptor.AuthInterceptor
import ru.eruditeonline.network.data.interceptor.BaseMetadataInterceptor
import ru.eruditeonline.network.data.interceptor.TokenAuthenticator
import ru.eruditeonline.network.data.typeadapter.GsonEnumConverterFactory
import ru.eruditeonline.network.di.util.RefreshTokenClient
import ru.eruditeonline.network.domain.repository.EndpointRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val CONNECTION_TIMEOUTS_MS = 5000L
private const val READ_WRITE_TIMEOUTS_MS = 20000L

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Context,
        baseMetadataInterceptor: BaseMetadataInterceptor,
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            setTimeouts()
            addInterceptor(baseMetadataInterceptor)
            addInterceptor(authInterceptor)
            authenticator(tokenAuthenticator)

            // логгер запросов в шторке для тестировния
            addInterceptor(ChuckerInterceptor.Builder(context).build())
        }.build()
    }

    @Provides
    @Singleton
    @RefreshTokenClient
    fun provideRefreshTokenOkHttpClient(
        context: Context,
        baseMetadataInterceptor: BaseMetadataInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            setTimeouts()
            addInterceptor(baseMetadataInterceptor)

            // логгер запросов в шторке для тестировния
            addInterceptor(ChuckerInterceptor.Builder(context).build())
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        endpointRepository: EndpointRepository,
        gson: Gson,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(endpointRepository.provideEndpoint())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(GsonEnumConverterFactory())
            .build()
    }

    @Singleton
    @Provides
    @RefreshTokenClient
    fun provideRefreshTokenRetrofit(
        endpointRepository: EndpointRepository,
        gson: Gson,
        @RefreshTokenClient client: OkHttpClient,
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(client)
            .baseUrl(endpointRepository.provideEndpoint())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(GsonEnumConverterFactory())
            .build()
    }

    private fun OkHttpClient.Builder.setTimeouts() {
        connectTimeout(CONNECTION_TIMEOUTS_MS, TimeUnit.MILLISECONDS)
        readTimeout(READ_WRITE_TIMEOUTS_MS, TimeUnit.MILLISECONDS)
        writeTimeout(READ_WRITE_TIMEOUTS_MS, TimeUnit.MILLISECONDS)
    }
}