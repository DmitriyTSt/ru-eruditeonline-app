package ru.eruditeonline.app.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.eruditeonline.app.data.remote.model.base.ApiCountry
import ru.eruditeonline.app.data.remote.model.base.ApiDiploma
import ru.eruditeonline.app.data.remote.model.rating.ApiRatingRow
import ru.eruditeonline.app.data.remote.model.test.ApiCreatedResult
import ru.eruditeonline.app.data.remote.model.test.ApiTempResult
import ru.eruditeonline.app.data.remote.model.test.ApiTestCommonResultRow
import ru.eruditeonline.app.data.remote.model.test.ApiTestUserResultRow
import ru.eruditeonline.app.data.remote.params.CompetitionCheckParams
import ru.eruditeonline.app.data.remote.params.CompetitionItemsParams
import ru.eruditeonline.app.data.remote.params.CreateAnonymParams
import ru.eruditeonline.app.data.remote.params.LoginParams
import ru.eruditeonline.app.data.remote.params.RatingParams
import ru.eruditeonline.app.data.remote.params.RegistrationParams
import ru.eruditeonline.app.data.remote.params.SaveResultParams
import ru.eruditeonline.app.data.remote.response.CompetitionItemData
import ru.eruditeonline.app.data.remote.response.CompetitionItemsResponse
import ru.eruditeonline.app.data.remote.response.CompetitionTestData
import ru.eruditeonline.app.data.remote.response.EmptyResponse
import ru.eruditeonline.app.data.remote.response.ListResponse
import ru.eruditeonline.app.data.remote.response.ObjectResponse
import ru.eruditeonline.app.data.remote.response.ProfileData
import ru.eruditeonline.app.data.remote.response.TestUserResultResponse
import ru.eruditeonline.app.data.remote.response.TokenData

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
    suspend fun saveResult(@Body params: SaveResultParams): ObjectResponse<ApiCreatedResult>

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

    @GET("results")
    suspend fun getCommonResults(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ListResponse<ApiTestCommonResultRow>

    @POST("rating")
    suspend fun getRating(@Body params: RatingParams): ListResponse<ApiRatingRow>

    @GET("countries")
    suspend fun getCountries(): ListResponse<ApiCountry>

    @GET("diplomas")
    suspend fun getDiplomas(): ListResponse<ApiDiploma>

    @POST("auth/anonym")
    suspend fun createAnonym(@Body params: CreateAnonymParams): ObjectResponse<TokenData>

    @GET("auth/confirm")
    suspend fun confirmEmail(@Query("token") token: String): EmptyResponse

    @POST("auth/login")
    suspend fun login(@Body params: LoginParams): ObjectResponse<TokenData>

    @GET("auth/logout")
    suspend fun logout(): ObjectResponse<TokenData>

    @POST("auth/registration")
    suspend fun registration(@Body params: RegistrationParams): EmptyResponse
}
