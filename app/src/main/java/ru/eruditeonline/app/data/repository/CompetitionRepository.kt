package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.competition.CompetitionItem
import ru.eruditeonline.app.data.model.competition.CompetitionPagingData

interface CompetitionRepository {
    /** Список конкурсов */
    suspend fun getCompetitionItems(
        query: String?,
        ageIds: List<String>?,
        subjectIds: List<String>?,
        offset: Int,
        limit: Int
    ): CompetitionPagingData

    /** Получение конкурса */
    suspend fun getCompetitionItem(id: String): CompetitionItem
}
