package io.github.ayonshafiul.foodreview.repository

import io.github.ayonshafiul.foodreview.api.RetrofitInstance
import io.github.ayonshafiul.foodreview.model.*
import retrofit2.Response

class Repository {
    suspend fun login(user : User): Response<TokenResponse> {
        return RetrofitInstance.foodAPI.login(user)
    }

    suspend fun checkAuthenticated(token: String): Response<MsgResponse> {
        return RetrofitInstance.foodAPI.checkAuthenticated(token)
    }

    suspend fun register(user: User): Response<MsgResponse> {
        return RetrofitInstance.foodAPI.register(user)
    }


    suspend fun getRestaurants(token: String) : Response<RestaurantResponse> {
        return RetrofitInstance.foodAPI.getTopRatedRestaurants(token);
    }

    suspend fun getTopRestaurants(token: String) : Response<RestaurantResponse> {
        return RetrofitInstance.foodAPI.getTopRatedRestaurants(token);
    }
    suspend fun getPopularRestaurants(token: String) : Response<RestaurantResponse> {
        return RetrofitInstance.foodAPI.getPopularRestaurants(token);
    }
    suspend fun getTopFoodItems(token: String) : Response<FoodResponse> {
        return RetrofitInstance.foodAPI.getTopRatedFoodItems(token);
    }
    suspend fun getPopularFoodItems(token: String) : Response<FoodResponse> {
        return RetrofitInstance.foodAPI.getPopularFoodItems(token);
    }

}