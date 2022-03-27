package ru.eruditeonline.app.domain.paging

import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.data.repository.CompetitionRepository
import ru.eruditeonline.app.domain.paging.base.BasePagingSource

class CompetitionItemsPagingSource(
    private val query: String?,
    private val ageIds: List<String>,
    private val subjectIds: List<String>,
    private val repository: CompetitionRepository,
    private val filterCallback: (CompetitionFilters) -> Unit,
) : BasePagingSource<CompetitionItemShort>() {
    override suspend fun loadPage(offset: Int, limit: Int): List<CompetitionItemShort> {
        val result = repository.getCompetitionItems(query, ageIds, subjectIds, offset, limit)
        if (offset == 0 && result.filters != null) {
            filterCallback(result.filters)
        }
        return result.list
    }
}
