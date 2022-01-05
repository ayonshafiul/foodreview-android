package io.github.ayonshafiul.foodreview.api

import io.github.ayonshafiul.foodreview.model.*
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

    @POST("/api/user/register")
    suspend fun register(@Body user: User): Response<MsgResponse>

    @GET("/api/restaurant/get")
    suspend fun getRestaurants(@Header("authorization") token: String): Response<RestaurantResponse>

    @GET("/api/restaurant/toprated")
    suspend fun getTopRatedRestaurants(@Header("authorization") token: String): Response<RestaurantResponse>

    @GET("/api/restaurant/popular")
    suspend fun getPopularRestaurants(@Header("authorization") token: String): Response<RestaurantResponse>

    @GET("/api/food/toprated")
    suspend fun getTopRatedFoodItems(@Header("authorization") token: String): Response<FoodResponse>

    @GET("/api/food/popular")
    suspend fun getPopularFoodItems(@Header("authorization") token: String): Response<FoodResponse>

}