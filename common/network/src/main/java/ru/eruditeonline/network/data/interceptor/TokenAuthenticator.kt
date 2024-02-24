package ru.eruditeonline.network.data.interceptor

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import ru.eruditeonline.network.data.util.RefreshTokenLock
import ru.eruditeonline.network.domain.model.AccessToken
import ru.eruditeonline.network.domain.repository.TokenRepository
import ru.eruditeonline.network.domain.usecase.RefreshTokenOrClearSessionUseCase
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val refreshTokenOrClearSessionUseCase: RefreshTokenOrClearSessionUseCase,
    private val tokenRepository: TokenRepository,
    private val refreshTokenLock: RefreshTokenLock,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val storedAccessToken = tokenRepository.accessToken

        synchronized(refreshTokenLock) {
            // потенциально, сюда может зайти запрос, когда уже есть новый аксес токен
            val potentiallyUpdatedAccessToken = tokenRepository.accessToken
            if (potentiallyUpdatedAccessToken != null && storedAccessToken != potentiallyUpdatedAccessToken) {
                // Токен был обновлен в другом потоке. Повторим запрос с новым токеном.
                return response.request().newBuilder()
                    .header(AuthInterceptor.HEADER_AUTHORIZATION, potentiallyUpdatedAccessToken.value)
                    .build()
            } else if (potentiallyUpdatedAccessToken == null) {
                // у нас случился локальный логаут. Это значит, что запрос ранее сделает всю работу,
                // а мы тут не должны выполнять рефреш
                throw IOException("Error refresh token")
            } else {
                // Рефрешим токен в фоне и отправляем снова запрос
                val updatedAccessToken: AccessToken? = try {
                    runBlocking { refreshTokenOrClearSessionUseCase.execute(Unit) }
                } catch (ex: Exception) {
                    // Механика локального разлогина случилась внутри  рефреша, поэтому тут ничего делать не нужно
                    Timber.e(ex)
                    null
                }

                if (updatedAccessToken != null) {
                    return response.request().newBuilder()
                        .header(AuthInterceptor.HEADER_AUTHORIZATION, updatedAccessToken.value)
                        .build()
                }
            }
        }
        // прокидываем 401 ошибку дальше
        return null
    }
}
