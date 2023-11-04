package ru.eruditeonline.app.domain.usecase.competition

import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.data.model.competition.CompetitionPage
import ru.eruditeonline.app.data.repository.CompetitionRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Получение страницы конкурсов с фильтрацией
 */
class GetCompetitionsPageUseCase @Inject constructor(
    private val competitionRepository: CompetitionRepository,
) : UseCaseUnary<GetCompetitionsPageUseCase.Params, CompetitionPage>() {

    override suspend fun execute(params: Params): CompetitionPage {
        return competitionRepository.getCompetitionItems(
            query = params.query?.takeIf { it.isNotBlank() },
            ageIds = params.ageIds,
            subjectIds = params.subjectIds,
            offset = params.offset,
            limit = params.limit,
        )
    }

    data class Params(
        val query: String?,
        val ageIds: List<String>,
        val subjectIds: List<String>,
        val offset: Int,
        val limit: Int,
        val filterCallback: (CompetitionFilters) -> Unit,
    )
}
