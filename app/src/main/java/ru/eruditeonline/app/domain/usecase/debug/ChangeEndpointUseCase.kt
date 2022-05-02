package ru.eruditeonline.app.domain.usecase.debug

import ru.eruditeonline.app.data.repository.EndpointRepository
import ru.eruditeonline.app.domain.usecase.auth.LogoutUseCase
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Изменение сервера
 *
 * Возвращает информацию, был ли изменен сервер
 */
class ChangeEndpointUseCase @Inject constructor(
    private val endpointRepository: EndpointRepository,
    private val logoutUseCase: LogoutUseCase,
) : UseCaseUnary<ChangeEndpointUseCase.Params, Boolean>() {

    override suspend fun execute(params: Params): Boolean {
        return if (params.newEndpoint != null && params.newEndpoint != endpointRepository.provideEndpoint()) {
            endpointRepository.updateEndpoint(params.newEndpoint)
            logoutUseCase.execute(Unit)
            true
        } else {
            false
        }
    }

    data class Params(
        val newEndpoint: String?,
    )
}