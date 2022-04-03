package ru.eruditeonline.app.domain.usecase

import ru.eruditeonline.app.data.repository.TokenRepository
import ru.eruditeonline.app.domain.usecase.auth.CreateAnonymUseCase
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

class SplashUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val createAnonymUseCase: CreateAnonymUseCase,
) : UseCaseUnary<Unit, Unit>() {
    override suspend fun execute(params: Unit) {
        val token = tokenRepository.accessToken
        if (token.isNullOrEmpty()) {
            createAnonymUseCase.execute(Unit)
        }
    }
}
