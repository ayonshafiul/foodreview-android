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

    @GET("/api/food/get/{foodID}")
    suspend fun getFoodDetails(@Header("authorization") token: String, @Path("foodID") foodID: Int): Response<FoodResponse>

    @GET("/api/restaurant/get/{restaurantID}")
    suspend fun getRestaurantDetails(@Header("authorization") token: String, @Path("restaurantID") foodID: Int): Response<RestaurantResponse>

    @GET("/api/restaurant/review/{restaurantID}")
    suspend fun getRestaurantReviews(@Header("authorization") token: String, @Path("restaurantID") foodID: Int): Response<ReviewResponse>

    @GET("/api/food/review/{foodID}")
    suspend fun getFoodReviews(@Header("authorization") token: String, @Path("foodID") foodID: Int): Response<ReviewResponse>


    @POST("/api/restaurant/review/{restaurantID}")
    suspend fun postRestaurantReview(
        @Header("authorization") token: String,
        @Body reviewBody: ReviewBody,
        @Path("restaurantID") restaurantID: Int
    ): Response<MsgResponse>

    @POST("/api/food/review/{foodID}")
    suspend fun postFoodReview(
        @Header("authorization") token: String,
        @Body reviewBody: ReviewBody,
        @Path("foodID") foodID: Int
    ): Response<MsgResponse>

    @GET("/api/food/search/")
    suspend fun searchFoodItems(@Header("authorization") token: String, @Query("q") query: String): Response<FoodResponse>

    @GET("/api/restaurant/search/")
    suspend fun searchRestaurants(@Header("authorization") token: String, @Query("q") query: String): Response<RestaurantResponse>

}