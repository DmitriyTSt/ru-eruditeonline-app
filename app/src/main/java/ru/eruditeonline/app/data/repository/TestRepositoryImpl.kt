package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.TestMapper
import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.data.model.test.CompetitionTest
import ru.eruditeonline.app.data.model.test.TempResult
import ru.eruditeonline.app.data.remote.ApiService
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val testMapper: TestMapper,
) : TestRepository {
    override suspend fun getCompetitionTest(id: String): CompetitionTest {
        return apiService.getCompetitionTest(id)
            .data
            .test
            ?.let { testMapper.fromApiToModel(it) }
            ?: throw IllegalStateException("Test data null from api")
    }

    override suspend fun checkTest(data: CompetitionPassData): TempResult {
        return apiService.checkTest(testMapper.fromModelToApi(data))
            .data
            .let { testMapper.fromApiToModel(it) }
    }
}
