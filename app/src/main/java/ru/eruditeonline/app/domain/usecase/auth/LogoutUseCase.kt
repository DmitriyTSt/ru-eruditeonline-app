package ru.eruditeonline.app.domain.usecase.auth

import ru.eruditeonline.app.data.repository.AuthRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Выход
 */
class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val saveTokenUseCase: SaveTokenUseCase,
) : UseCaseUnary<Unit, Unit>() {

    override suspend fun execute(params: Unit) {
        val token = authRepository.logout()
        saveTokenUseCase.execute(SaveTokenUseCase.Params(token))
    }
}