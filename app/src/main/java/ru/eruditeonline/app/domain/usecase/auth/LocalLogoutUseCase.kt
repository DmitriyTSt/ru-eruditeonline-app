package ru.eruditeonline.app.domain.usecase.auth

import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Локальный выход
 */
class LocalLogoutUseCase @Inject constructor(
    // Что нужно чистить
) : UseCaseUnary<Unit, Unit>() {

    override suspend fun execute(params: Unit) {

    }
}