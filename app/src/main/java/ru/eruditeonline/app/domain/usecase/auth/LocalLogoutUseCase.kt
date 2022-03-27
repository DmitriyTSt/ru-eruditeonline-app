package ru.eruditeonline.app.domain.usecase.auth

import ru.eruditeonline.app.data.preferences.base.RegularPreferenceStorage
import ru.eruditeonline.app.data.preferences.base.SecuredPreferenceStorage
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Локальный выход
 */
class LocalLogoutUseCase @Inject constructor(
    private val securedPreferencesStorage: SecuredPreferenceStorage,
    private val regularPreferenceStorage: RegularPreferenceStorage,
) : UseCaseUnary<Unit, Unit>() {

    override suspend fun execute(params: Unit) {
        securedPreferencesStorage.clear(commitNow = true)
        regularPreferenceStorage.clear(commitNow = true)
    }
}
