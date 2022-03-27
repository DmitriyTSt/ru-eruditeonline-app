package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.data.model.test.CompetitionTest
import ru.eruditeonline.app.data.model.test.TempResult

interface TestRepository {
    /** Получение теста для прохождения */
    suspend fun getCompetitionTest(id: String): CompetitionTest

    /** Проверка теста */
    suspend fun checkTest(data: CompetitionPassData): TempResult
}
