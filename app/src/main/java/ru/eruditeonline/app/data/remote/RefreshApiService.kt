package ru.eruditeonline.app.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import ru.eruditeonline.app.data.remote.params.RefreshTokenParams
import ru.eruditeonline.app.data.remote.response.ObjectResponse
import ru.eruditeonline.app.data.remote.response.TokenData

interface RefreshApiService {
    @POST("v1/auth/refresh")
    suspend fun refreshToken(@Body params: RefreshTokenParams): ObjectResponse<TokenData>
}
