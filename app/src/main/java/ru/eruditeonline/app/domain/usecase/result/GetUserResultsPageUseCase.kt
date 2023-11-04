package ru.eruditeonline.app.domain.usecase.result

import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Получение страницы результатов пользователя
 */
class GetUserResultsPageUseCase @Inject constructor(
    private val resultRepository: ResultRepository,
) : UseCaseUnary<GetUserResultsPageUseCase.Params, List<TestUserResultRow>>() {

    override suspend fun execute(params: Params): List<TestUserResultRow> {
        val query = params.query?.takeIf { it.isNotEmpty() }
        return resultRepository.getUserResults(query, params.offset, params.limit)
    }

    data class Params(
        val query: String? = null,
        val offset: Int,
        val limit: Int,
    )
}
