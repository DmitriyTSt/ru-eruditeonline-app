package ru.eruditeonline.app.domain.usecase.test

import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.data.model.test.TempResult
import ru.eruditeonline.app.data.repository.TestRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Проверка теста
 */
class CheckTestUseCase @Inject constructor(
    private val testRepository: TestRepository,
) : UseCaseUnary<CheckTestUseCase.Params, TempResult>() {

    override suspend fun execute(params: Params): TempResult {
        return testRepository.checkTest(params.passData)
    }

    data class Params(
        val passData: CompetitionPassData,
    )
}
