package ru.eruditeonline.app.domain.usecase.result

import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Получение страницы общих результатов
 */
class GetCommonResultsPageUseCase @Inject constructor(
    private val resultRepository: ResultRepository,
) : UseCaseUnary<GetCommonResultsPageUseCase.Params, List<TestCommonResultRow>> {

    override suspend fun execute(params: Params): List<TestCommonResultRow> {
        return resultRepository.getCommonResults(params.offset, params.limit)
    }

    data class Params(
        val offset: Int,
        val limit: Int,
    )
}
