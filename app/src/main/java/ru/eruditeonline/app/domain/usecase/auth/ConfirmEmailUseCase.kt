package ru.eruditeonline.app.domain.usecase.auth

import ru.eruditeonline.app.data.repository.AuthRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Подтверждение email адреса
 */
class ConfirmEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : UseCaseUnary<ConfirmEmailUseCase.Params, Unit>() {

    override suspend fun execute(params: Params) {
        authRepository.confirmEmail(params.token)
    }

    data class Params(
        val token: String,
    )
}
