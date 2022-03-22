package ru.eruditeonline.app.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.eruditeonline.app.data.remote.model.test.ApiCreatedResult
import ru.eruditeonline.app.data.remote.model.test.ApiTempResult
import ru.eruditeonline.app.data.remote.model.test.ApiTestUserResultRow
import ru.eruditeonline.app.data.remote.params.CompetitionCheckParams
import ru.eruditeonline.app.data.remote.params.CompetitionItemsParams
import ru.eruditeonline.app.data.remote.params.ResultSaveParams
import ru.eruditeonline.app.data.remote.response.CompetitionItemData
import ru.eruditeonline.app.data.remote.response.CompetitionItemsResponse
import ru.eruditeonline.app.data.remote.response.CompetitionTestData
import ru.eruditeonline.app.data.remote.response.ListResponse
import ru.eruditeonline.app.data.remote.response.ObjectResponse
import ru.eruditeonline.app.data.remote.response.ProfileData
import ru.eruditeonline.app.data.remote.response.TestUserResultResponse

interface ApiService {
    @POST("competition/items")
    suspend fun getCompetitionItems(@Body params: CompetitionItemsParams): ObjectResponse<CompetitionItemsResponse>

    @GET("competition/item/{id}")
    suspend fun getCompetitionItem(@Path("id") id: String): ObjectResponse<CompetitionItemData>

    @GET("competition/test/{id}")
    suspend fun getCompetitionTest(@Path("id") id: String): ObjectResponse<CompetitionTestData>

    @POST("competition/check")
    suspend fun checkTest(@Body params: CompetitionCheckParams): ObjectResponse<ApiTempResult>

    @POST("result/save")
    suspend fun saveResult(@Body params: ResultSaveParams): ObjectResponse<ApiCreatedResult>

    @GET("profile")
    suspend fun getProfile(): ObjectResponse<ProfileData>

    @GET("user/results")
    suspend fun getUserResults(
        @Query("email") email: String?,
        @Query("query") query: String?,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): ListResponse<ApiTestUserResultRow>

    @GET("results/{id}")
    suspend fun getResult(@Path("id") id: String): ObjectResponse<TestUserResultResponse>
}
