package ru.eruditeonline.app.domain.usecase

import kotlinx.coroutines.delay
import ru.eruditeonline.app.data.model.base.AppUpdate
import ru.eruditeonline.app.data.repository.TokenRepository
import ru.eruditeonline.app.domain.usecase.auth.CreateAnonymUseCase
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

class SplashUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val createAnonymUseCase: CreateAnonymUseCase,
    private val getAppConfigUseCase: GetAppConfigUseCase,
) : UseCaseUnary<Unit, SplashUseCase.Result>() {

    override suspend fun execute(params: Unit): Result {
        val token = tokenRepository.accessToken
        if (token.isNullOrEmpty()) {
            createAnonymUseCase.execute(Unit)
        }
        val appConfig = getAppConfigUseCase.execute(Unit)
        return if (appConfig.appUpdate != null) {
            Result.AppUpdateScreen(appConfig.appUpdate)
        } else {
            Result.MainScreen
        }
    }

    sealed class Result {
        object MainScreen : Result()
        data class AppUpdateScreen(val appUpdate: AppUpdate) : Result()
    }
}
