package ru.eruditeonline.app.domain.usecase.result

import ru.eruditeonline.app.data.model.test.TestUserResult
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Получение результата
 */
class GetResultUseCase @Inject constructor(
    private val resultRepository: ResultRepository,
) : UseCaseUnary<GetResultUseCase.Params, TestUserResult> {

    override suspend fun execute(params: Params): TestUserResult {
        return resultRepository.getResult(params.id)
    }

    data class Params(
        val id: Int,
    )
}
