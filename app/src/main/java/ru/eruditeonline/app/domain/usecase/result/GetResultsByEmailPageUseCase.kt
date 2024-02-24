package ru.eruditeonline.app.domain.usecase.result

import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Получение страницы результатов по email
 */
class GetResultsByEmailPageUseCase @Inject constructor(
    private val resultRepository: ResultRepository,
) : UseCaseUnary<GetResultsByEmailPageUseCase.Params, List<TestUserResultRow>> {

    override suspend fun execute(params: Params): List<TestUserResultRow> {
        return resultRepository.getResultsByEmail(params.email, params.offset, params.limit)
    }

    data class Params(
        val email: String,
        val offset: Int,
        val limit: Int,
    )
}
