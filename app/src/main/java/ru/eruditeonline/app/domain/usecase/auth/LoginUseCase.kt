package ru.eruditeonline.app.domain.usecase.auth

import ru.eruditeonline.app.data.repository.AuthRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Авторизация
 */
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val saveTokenUseCase: SaveTokenUseCase,
) : UseCaseUnary<LoginUseCase.Params, Unit>() {

    override suspend fun execute(params: Params) {
        val token = authRepository.login(params.login, params.password)
        saveTokenUseCase.execute(SaveTokenUseCase.Params(token))
    }

    data class Params(
        val login: String,
        val password: String,
    )
}