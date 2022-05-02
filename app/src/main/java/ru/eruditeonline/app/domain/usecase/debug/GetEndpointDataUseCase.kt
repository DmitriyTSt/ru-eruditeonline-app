package ru.eruditeonline.app.domain.usecase.debug

import ru.eruditeonline.app.data.repository.EndpointRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Получение настроек сервера
 */
class GetEndpointDataUseCase @Inject constructor(
    private val endpointRepository: EndpointRepository,
) : UseCaseUnary<Unit, GetEndpointDataUseCase.Result>() {
    override suspend fun execute(params: Unit): Result {
        return Result(
            currentEndpoint = endpointRepository.provideEndpoint(),
            prodEndpoint = endpointRepository.provideProdEndpoint(),
            devEndpoint = endpointRepository.provideDevEndpoint(),
        )
    }

    data class Result(
        val currentEndpoint: String,
        val prodEndpoint: String,
        val devEndpoint: String,
    )
}