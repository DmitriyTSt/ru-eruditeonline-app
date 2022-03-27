package ru.eruditeonline.app.domain.usecase.auth

import ru.eruditeonline.app.data.model.auth.Token
import ru.eruditeonline.app.data.repository.TokenRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Сохранение пары токенов
 */
class SaveTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
) : UseCaseUnary<SaveTokenUseCase.Params, Unit>() {

    override suspend fun execute(params: Params) {
        tokenRepository.accessToken = params.token.accessToken
        tokenRepository.refreshToken = params.token.refreshToken
    }

    data class Params(
        val token: Token,
    )
}
