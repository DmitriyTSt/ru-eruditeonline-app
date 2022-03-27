package ru.eruditeonline.app.domain.usecase.competition

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.repository.CompetitionRepository
import ru.eruditeonline.app.domain.paging.CompetitionItemsPagingSource
import ru.eruditeonline.app.domain.paging.base.createPager
import ru.eruditeonline.app.domain.usecase.base.UseCasePaged
import javax.inject.Inject

/**
 * Список конкурсов с фильтрацией
 */
class FilterCompetitionsUseCase @Inject constructor(
    private val competitionRepository: CompetitionRepository,
) : UseCasePaged<FilterCompetitionsUseCase.Params, CompetitionItemShort>() {

    override fun execute(params: Params): Flow<PagingData<CompetitionItemShort>> {
        return createPager(
            CompetitionItemsPagingSource(
                query = params.query.takeIf { it.isNotBlank() },
                ageIds = params.ageIds,
                subjectIds = params.subjectIds,
                repository = competitionRepository,
                filterCallback = params.filterCallback
            )
        ).flow
    }

    data class Params(
        val query: String,
        val ageIds: List<String>,
        val subjectIds: List<String>,
        val filterCallback: (CompetitionFilters) -> Unit,
    )
}
