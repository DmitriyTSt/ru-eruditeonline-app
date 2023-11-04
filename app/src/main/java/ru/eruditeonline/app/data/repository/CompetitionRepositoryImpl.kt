package ru.eruditeonline.app.data.repository

import kotlinx.coroutines.delay
import ru.eruditeonline.app.data.mapper.CompetitionMapper
import ru.eruditeonline.app.data.model.competition.CompetitionItem
import ru.eruditeonline.app.data.model.competition.CompetitionPage
import ru.eruditeonline.app.data.remote.ApiService
import ru.eruditeonline.app.data.remote.params.CompetitionItemsParams
import javax.inject.Inject

class CompetitionRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val competitionMapper: CompetitionMapper,
) : CompetitionRepository {
    override suspend fun getCompetitionItems(
        query: String?,
        ageIds: List<String>?,
        subjectIds: List<String>?,
        offset: Int,
        limit: Int,
    ): CompetitionPage {
        delay(1000) // TODO remove
        return apiService.getCompetitionItems(
            CompetitionItemsParams(
                query,
                ageIds,
                subjectIds,
                offset,
                limit
            )
        ).data.let { competitionMapper.fromApiToModel(it) }
    }

    override suspend fun getCompetitionItem(id: Int): CompetitionItem {
        return apiService.getCompetitionItem(id)
            .data
            .item
            ?.let { competitionMapper.fromApiToModel(it) }
            ?: throw IllegalStateException("Competition item data null from api")
    }
}
