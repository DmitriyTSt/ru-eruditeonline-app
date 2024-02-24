package ru.eruditeonline.network.domain.usecase

import ru.eruditeonline.network.domain.model.AccessToken
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

class RefreshTokenOrClearSessionUseCase @Inject constructor(
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val clearSessionUseCase: ClearSessionUseCase,
) : UseCaseUnary<Unit, AccessToken> {
    override suspend fun execute(params: Unit): AccessToken {
        try {
            return refreshTokenUseCase.execute(Unit)
        } catch (e: Throwable) {
            if (e is RefreshTokenUseCase) {
                // запускаем перезапуск приложения с очисткой данных пользователя
                clearSessionUseCase.execute(Unit)
            }
            throw e
        }
    }
}

//private fun Throwable.isRefreshTokenError(): Boolean {
//    return if (this is HttpException) {
//        val error = response()?.errorBody()?.string()
//        try {
//            val jsonError = JSONObject(error.orEmpty())
//            val code = jsonError.getJSONObject(ERROR_FIELD_ERROR).getString(ERROR_FIELD_CODE)
//            code == ERROR_CODE_WRONG_TOKEN
//        } catch (e: JsonParseException) {
//            false
//        }
//    } else {
//        false
//    }
//}