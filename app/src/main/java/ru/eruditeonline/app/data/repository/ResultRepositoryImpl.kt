package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.TestMapper
import ru.eruditeonline.app.data.mapper.orDefault
import ru.eruditeonline.app.data.model.test.CreatedResult
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.data.model.test.TestUserResult
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.data.remote.ApiService
import ru.eruditeonline.app.data.remote.params.SaveResultParams
import ru.eruditeonline.app.data.remote.response.ListResponse
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val testMapper: TestMapper,
) : ResultRepository {
    override suspend fun getUserResults(
        email: String?,
        query: String?,
        offset: Int,
        limit: Int
    ): ListResponse<TestUserResultRow> {
        return apiService.getUserResults(email, query, offset, limit).data?.let { data ->
            ListResponse(
                ListResponse.Data(
                    list = data.list.orEmpty().map { testMapper.fromApiToModel(it) },
                    hasMore = data.hasMore.orDefault(),
                )
            )
        } ?: throw IllegalStateException("User results data null from api")
    }

    override suspend fun getCommonResults(offset: Int, limit: Int): ListResponse<TestCommonResultRow> {
        return apiService.getCommonResults(offset, limit).data?.let { data ->
            ListResponse(
                ListResponse.Data(
                    list = data.list.orEmpty().map { testMapper.fromApiToModel(it) },
                    hasMore = data.hasMore.orDefault(),
                )
            )
        } ?: throw IllegalStateException("Common results data null from api")
    }

    override suspend fun getResult(id: String): TestUserResult {
        return apiService.getResult(id)
            .data
            .result
            ?.let { testMapper.fromApiToModel(it) }
            ?: throw IllegalStateException("Result data null from api")
    }

    override suspend fun saveResult(
        completeId: Int,
        name: String,
        surname: String,
        patronymic: String?,
        school: String?,
        position: String?,
        teacher: String?,
        country: String,
        city: String,
        region: String?,
        email: String,
        teacherEmail: String?,
        diplomaType: String,
        quality: Int?,
        difficulty: Int?,
        interest: Int?
    ): CreatedResult {
        return apiService.saveResult(
            SaveResultParams(
                completeId = completeId,
                name = name,
                surname = surname,
                patronymic = patronymic,
                school = school,
                position = position,
                teacher = teacher,
                country = country,
                city = city,
                region = region,
                email = email,
                teacherEmail = teacherEmail,
                diplomaType = diplomaType,
                review = SaveResultParams.Review(
                    quality = quality,
                    difficulty = difficulty,
                    interest = interest,
                )
            )
        ).data.let { testMapper.fromApiToModel(it) }
    }
}
