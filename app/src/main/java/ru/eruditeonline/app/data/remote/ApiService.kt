package ru.eruditeonline.app.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.eruditeonline.app.data.remote.params.CompetitionItemsParams
import ru.eruditeonline.app.data.remote.response.CompetitionItemData
import ru.eruditeonline.app.data.remote.response.CompetitionItemsResponse
import ru.eruditeonline.app.data.remote.response.ObjectResponse

interface ApiService {
    @POST("competition/items")
    fun getCompetitionItems(@Body params: CompetitionItemsParams): ObjectResponse<CompetitionItemsResponse>

    @GET("competition/item/{id}")
    fun getCompetitionItem(@Path("id") id: String): ObjectResponse<CompetitionItemData>
}
