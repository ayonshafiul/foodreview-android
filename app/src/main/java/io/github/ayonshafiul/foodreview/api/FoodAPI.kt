package io.github.ayonshafiul.foodreview.api

import io.github.ayonshafiul.foodreview.model.MsgResponse
import io.github.ayonshafiul.foodreview.model.TokenResponse
import io.github.ayonshafiul.foodreview.model.User
import retrofit2.Response
import retrofit2.http.*

interface FoodAPI {
    @POST("/api/user/login")
    suspend fun login(
        @Body user: User
    ): Response<TokenResponse>


    @GET("/api/user/authenticated")
    suspend fun checkAuthenticated(
        @Header("authorization") token: String
    ) : Response<MsgResponse>
}