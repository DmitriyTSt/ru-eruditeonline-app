package ru.eruditeonline.app.presentation.ui.competition.items

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.eruditeonline.app.data.model.competition.CompetitionFilters
import ru.eruditeonline.app.data.model.competition.CompetitionItemShort
import ru.eruditeonline.app.domain.usecase.competition.GetCompetitionsPageUseCase
import ru.eruditeonline.app.presentation.paging.base.BasePagingFlowFactory
import javax.inject.Inject

class CompetitionItemsPagingFlowFactory @Inject constructor(
    private val getCompetitionsPageUseCase: GetCompetitionsPageUseCase,
) : BasePagingFlowFactory<CompetitionItemsPagingFlowFactory.Params, Int, CompetitionItemShort>() {

    override fun create(params: Params): Flow<PagingData<CompetitionItemShort>> {
        return createPager(
            CompetitionItemsPagingSource(
                query = params.query,
                ageIds = params.ageIds,
                subjectIds = params.subjectIds,
                filterCallback = params.filterCallback,
                getCompetitionsPageUseCase = getCompetitionsPageUseCase,
            )
        ).flow
    }

    data class Params(
        val query: String?,
        val ageIds: List<String>,
        val subjectIds: List<String>,
        val filterCallback: (CompetitionFilters) -> Unit,
    )
}
