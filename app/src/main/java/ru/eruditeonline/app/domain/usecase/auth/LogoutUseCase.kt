package ru.eruditeonline.app.domain.usecase.auth

import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.app.data.repository.AuthRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Выход
 */
class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val preferencesStorage: PreferencesStorage,
) : UseCaseUnary<Unit, Unit> {

    override suspend fun execute(params: Unit) {
        val token = authRepository.logout()
        saveTokenUseCase.execute(SaveTokenUseCase.Params(token))
        preferencesStorage.isSignedIn = false
    }
}
