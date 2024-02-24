package ru.eruditeonline.app.domain.usecase.auth

import ru.eruditeonline.app.data.model.auth.Token
import ru.eruditeonline.network.domain.model.AccessToken
import ru.eruditeonline.network.domain.model.RefreshToken
import ru.eruditeonline.network.domain.repository.TokenRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Сохранение пары токенов
 */
class SaveTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
) : UseCaseUnary<SaveTokenUseCase.Params, Unit> {

    override suspend fun execute(params: Params) {
        tokenRepository.accessToken = AccessToken(params.token.accessToken)
        tokenRepository.refreshToken = RefreshToken(params.token.refreshToken)
    }

    data class Params(
        val token: Token,
    )
}
