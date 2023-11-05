package ru.eruditeonline.app.domain.usecase.debug

import ru.eruditeonline.app.data.repository.EndpointRepository
import ru.eruditeonline.app.domain.repository.DebugRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Получение настроек для дебаг экрана
 */
class GetDebugDataUseCase @Inject constructor(
    private val endpointRepository: EndpointRepository,
    private val debugRepository: DebugRepository,
) : UseCaseUnary<Unit, GetDebugDataUseCase.Result>() {

    override suspend fun execute(params: Unit): Result {
        val currentEndpoint = endpointRepository.provideEndpoint()
        val prodEndpoint = endpointRepository.provideProdEndpoint()
        val devEndpoint = endpointRepository.provideDevEndpoint()
        val isComposeEnabled = debugRepository.isComposeEnabled()
        val customEndpoint = currentEndpoint.takeIf { it != devEndpoint && it != prodEndpoint }.orEmpty()
        return Result(
            prodEndpoint = prodEndpoint,
            prodEndpointEnabled = prodEndpoint == currentEndpoint,
            devEndpoint = devEndpoint,
            devEndpointEnabled = devEndpoint == currentEndpoint,
            customEndpoint = customEndpoint,
            customEndpointEnabled = customEndpoint.isNotEmpty(),
            isComposeEnabled = isComposeEnabled,
        )
    }

    data class Result(
        val prodEndpoint: String,
        val prodEndpointEnabled: Boolean,
        val devEndpoint: String,
        val devEndpointEnabled: Boolean,
        val customEndpoint: String,
        val customEndpointEnabled: Boolean,
        val isComposeEnabled: Boolean,
    )
}
