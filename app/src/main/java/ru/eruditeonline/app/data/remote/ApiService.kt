package ru.eruditeonline.app.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.eruditeonline.app.data.remote.model.base.ApiAppConfig
import ru.eruditeonline.app.data.remote.model.base.ApiCountry
import ru.eruditeonline.app.data.remote.model.base.ApiDiploma
import ru.eruditeonline.app.data.remote.model.base.ApiWebPageItem
import ru.eruditeonline.app.data.remote.model.main.ApiMainSection
import ru.eruditeonline.app.data.remote.model.rating.ApiRatingRow
import ru.eruditeonline.app.data.remote.model.test.ApiCreatedResult
import ru.eruditeonline.app.data.remote.model.test.ApiTempResult
import ru.eruditeonline.app.data.remote.model.test.ApiTestCommonResultRow
import ru.eruditeonline.app.data.remote.model.test.ApiTestUserResultRow
import ru.eruditeonline.app.data.remote.params.CompetitionCheckParams
import ru.eruditeonline.app.data.remote.params.CompetitionItemsParams
import ru.eruditeonline.app.data.remote.params.CreateAnonymParams
import ru.eruditeonline.app.data.remote.params.GetAppConfigParams
import ru.eruditeonline.app.data.remote.params.LoginParams
import ru.eruditeonline.app.data.remote.params.RatingParams
import ru.eruditeonline.app.data.remote.params.RegistrationParams
import ru.eruditeonline.app.data.remote.params.SaveResultParams
import ru.eruditeonline.app.data.remote.response.CompetitionItemData
import ru.eruditeonline.app.data.remote.response.CompetitionItemsData
import ru.eruditeonline.app.data.remote.response.CompetitionTestData
import ru.eruditeonline.app.data.remote.response.EmptyResponse
import ru.eruditeonline.app.data.remote.response.ListResponse
import ru.eruditeonline.app.data.remote.response.ObjectResponse
import ru.eruditeonline.app.data.remote.response.ProfileData
import ru.eruditeonline.app.data.remote.response.TestUserResultResponse
import ru.eruditeonline.app.data.remote.response.TokenData
import ru.eruditeonline.app.data.remote.response.WebPageResponse

interface ApiService {
    @GET("v1/main")
    suspend fun getMainSections(): ListResponse<ApiMainSection>

    @POST("v1/competition/items")
    suspend fun getCompetitionItems(@Body params: CompetitionItemsParams): ObjectResponse<CompetitionItemsData>

    @GET("v1/competition/item/{id}")
    suspend fun getCompetitionItem(@Path("id") id: Int): ObjectResponse<CompetitionItemData>

    @GET("v1/competition/test/{id}")
    suspend fun getCompetitionTest(@Path("id") id: String): ObjectResponse<CompetitionTestData>

    @POST("v1/competition/check")
    suspend fun checkTest(@Body params: CompetitionCheckParams): ObjectResponse<ApiTempResult>

    @POST("v1/result/save")
    suspend fun saveResult(@Body params: SaveResultParams): ObjectResponse<ApiCreatedResult>

    @GET("v1/profile")
    suspend fun getProfile(): ObjectResponse<ProfileData>

    @GET("v1/user/results")
    suspend fun getUserResults(
        @Query("query") query: String?,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): ListResponse<ApiTestUserResultRow>

    @GET("v1/user/resultsByEmail")
    suspend fun getResultsByEmail(
        @Query("email") email: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): ListResponse<ApiTestUserResultRow>

    @GET("v1/result/{id}")
    suspend fun getResult(@Path("id") id: Int): ObjectResponse<TestUserResultResponse>

    @GET("v1/results")
    suspend fun getCommonResults(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): ListResponse<ApiTestCommonResultRow>

    @POST("v1/rating")
    suspend fun getRating(@Body params: RatingParams): ListResponse<ApiRatingRow>

    @GET("v1/countries")
    suspend fun getCountries(): ListResponse<ApiCountry>

    @GET("v1/diplomas")
    suspend fun getDiplomas(): ListResponse<ApiDiploma>

    @POST("v1/auth/anonym")
    suspend fun createAnonym(@Body params: CreateAnonymParams): ObjectResponse<TokenData>

    @GET("v1/auth/confirm")
    suspend fun confirmEmail(@Query("token") token: String): EmptyResponse

    @POST("v1/auth/login")
    suspend fun login(@Body params: LoginParams): ObjectResponse<TokenData>

    @GET("v1/auth/logout")
    suspend fun logout(): ObjectResponse<TokenData>

    @POST("v1/auth/registration")
    suspend fun registration(@Body params: RegistrationParams): EmptyResponse

    @GET("v1/webpages")
    suspend fun getWebPages(): ListResponse<ApiWebPageItem>

    @GET("v1/webpage")
    suspend fun getWebPage(@Query("path") path: String): ObjectResponse<WebPageResponse>

    @POST("v1/app/config")
    suspend fun getAppConfig(@Body params: GetAppConfigParams): ObjectResponse<ApiAppConfig>
}
