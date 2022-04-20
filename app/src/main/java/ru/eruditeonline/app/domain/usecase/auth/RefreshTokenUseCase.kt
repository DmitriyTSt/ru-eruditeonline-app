package ru.eruditeonline.app.domain.usecase.auth

import com.google.gson.JsonParseException
import org.json.JSONObject
import retrofit2.HttpException
import ru.eruditeonline.app.data.repository.RefreshTokenRepository
import ru.eruditeonline.app.data.repository.TokenRepository
import ru.eruditeonline.app.data.resource.DeviceUuidFactory
import ru.eruditeonline.app.domain.usecase.ClearSessionUseCase
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

private const val ERROR_CODE_WRONG_TOKEN = "WRONG_TOKEN"
private const val ERROR_FIELD_ERROR = "error"
private const val ERROR_FIELD_CODE = "code"

/**
 * Рефреш токена
 */
class RefreshTokenUseCase @Inject constructor(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenRepository: TokenRepository,
    private val deviceUuidFactory: DeviceUuidFactory,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val clearSessionUseCase: ClearSessionUseCase,
) : UseCaseUnary<Unit, String>() {
    override suspend fun execute(params: Unit): String {
        try {
            val token = refreshTokenRepository.refreshToken(
                deviceId = deviceUuidFactory.deviceUuid.toString(),
                refreshToken = tokenRepository.refreshToken.orEmpty(),
            )
            saveTokenUseCase.execute(SaveTokenUseCase.Params(token))
            return token.accessToken
        } catch (e: Throwable) {
            if (e.isRefreshTokenError()) {
                // запускаем перезапуск приложения с очисткой данных пользователя
                clearSessionUseCase.execute(Unit)
            }
            throw e
        }
    }
}

private fun Throwable.isRefreshTokenError(): Boolean {
    return if (this is HttpException) {
        val error = response()?.errorBody()?.string()
        try {
            val jsonError = JSONObject(error.orEmpty())
            val code = jsonError.getJSONObject(ERROR_FIELD_ERROR).getString(ERROR_FIELD_CODE)
            code == ERROR_CODE_WRONG_TOKEN
        } catch (e: JsonParseException) {
            false
        }
    } else {
        false
    }
}
