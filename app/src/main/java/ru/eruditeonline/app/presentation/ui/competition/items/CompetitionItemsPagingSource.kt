package ru.eruditeonline.app.presentation.ui.competition.items

import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.domain.usecase.competition.GetCompetitionsPageUseCase
import ru.eruditeonline.app.presentation.paging.base.BasePagingSource

class CompetitionItemsPagingSource(
    private val query: String?,
    private val ageIds: List<String>,
    private val subjectIds: List<String>,
    private val filterCallback: (CompetitionFilters) -> Unit,
    private val getCompetitionsPageUseCase: GetCompetitionsPageUseCase,
) : BasePagingSource<CompetitionItemShort>() {
    override suspend fun loadPage(offset: Int, limit: Int): List<CompetitionItemShort> {
        val result = getCompetitionsPageUseCase.execute(
            GetCompetitionsPageUseCase.Params(
                query = query,
                ageIds = ageIds,
                subjectIds = subjectIds,
                offset = offset,
                limit = limit,
                filterCallback = filterCallback,
            )
        )
        if (offset == 0 && result.filters != null) {
            filterCallback(result.filters)
        }
        return result.list
    }
}
