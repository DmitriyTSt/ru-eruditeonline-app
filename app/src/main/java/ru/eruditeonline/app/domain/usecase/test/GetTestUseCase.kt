package ru.eruditeonline.app.domain.usecase.test

import ru.eruditeonline.app.data.model.test.CompetitionTest
import ru.eruditeonline.app.data.repository.TestRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Получение теста для прохождения
 */
class GetTestUseCase @Inject constructor(
    private val testRepository: TestRepository,
) : UseCaseUnary<GetTestUseCase.Params, CompetitionTest> {

    override suspend fun execute(params: Params): CompetitionTest {
        return testRepository.getCompetitionTest(params.id)
    }

    data class Params(
        val id: String,
    )
}
