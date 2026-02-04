package ru.eruditeonline.app.data.remote.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import ru.eruditeonline.app.data.repository.TokenRepository
import ru.eruditeonline.app.di.module.ApiServiceModule
import ru.eruditeonline.app.di.module.RefreshTokenLock
import ru.eruditeonline.app.domain.usecase.auth.RefreshTokenUseCase
import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val tokenRepository: TokenRepository,
    private val refreshTokenLock: RefreshTokenLock,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            val storedAccessToken = tokenRepository.accessToken

            synchronized(refreshTokenLock) {
                // потенциально, сюда может зайти запрос, когда уже есть новый аксес токен
                val potentiallyUpdatedAccessToken = tokenRepository.accessToken
                if (potentiallyUpdatedAccessToken != null && storedAccessToken != potentiallyUpdatedAccessToken) {
                    // Токен был обновлен в другом потоке. Повторим запрос с новым токеном.
                    return response.request.newBuilder()
                        .header(ApiServiceModule.HEADER_AUTHORIZATION, potentiallyUpdatedAccessToken)
                        .build()
                } else if (potentiallyUpdatedAccessToken == null) {
                    // у нас случился локальный логаут. Это значит, что запрос ранее сделает всю работу,
                    // а мы тут не должны выполнять рефреш
                    throw IOException("Error refresh token")
                } else {
                    // Рефрешим токен в фоне и отправляем снова запрос
                    val updatedAccessToken: String? = try {
                        runBlocking { refreshTokenUseCase.execute(Unit) }
                    } catch (ex: Exception) {
                        // Механика локального разлогина случилась внутри  рефреша, поэтому тут ничего делать не нужно
                        Timber.e(ex)
                        null
                    }

                    if (updatedAccessToken != null) {
                        return response.request.newBuilder()
                            .header(ApiServiceModule.HEADER_AUTHORIZATION, updatedAccessToken)
                            .build()
                    }
                }
            }
        }
        // прокидываем 401 ошибку дальше
        return null
    }
}
