package ru.eruditeonline.app.domain.usecase.auth

import ru.eruditeonline.app.data.repository.AuthRepository
import ru.eruditeonline.app.data.resource.DeviceUuidFactory
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Создание анонимного пользователя
 */
class CreateAnonymUseCase @Inject constructor(
    private val deviceUuidFactory: DeviceUuidFactory,
    private val authRepository: AuthRepository,
    private val saveTokenUseCase: SaveTokenUseCase,
) : UseCaseUnary<Unit, Unit>() {

    override suspend fun execute(params: Unit) {
        val token = authRepository.createAnonym(deviceUuidFactory.deviceUuid.toString())
        saveTokenUseCase.execute(SaveTokenUseCase.Params(token))
    }
}