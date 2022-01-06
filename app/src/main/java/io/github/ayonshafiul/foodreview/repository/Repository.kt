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

    suspend fun getFoodDetails(token: String, foodID: Int) : Response<FoodResponse> {
        return RetrofitInstance.foodAPI.getFoodDetails(token, foodID);
    }

    suspend fun getRestaurantDetails(token: String, restaurantID: Int) : Response<RestaurantResponse> {
        return RetrofitInstance.foodAPI.getRestaurantDetails(token, restaurantID);
    }

    suspend fun getRestaurantReviews(token: String, restaurantID: Int) : Response<ReviewResponse> {
        return RetrofitInstance.foodAPI.getRestaurantReviews(token, restaurantID);
    }

    suspend fun getFoodReviews(token: String, foodID: Int) : Response<ReviewResponse> {
        return RetrofitInstance.foodAPI.getFoodReviews(token, foodID);
    }

    suspend fun postFoodReview(token : String, reviewBody: ReviewBody, foodID: Int): Response<MsgResponse> {
        return RetrofitInstance.foodAPI.postFoodReview(token, reviewBody, foodID)
    }

    suspend fun postRestaurantReview(token : String, reviewBody: ReviewBody, restaurantID: Int): Response<MsgResponse> {
        return RetrofitInstance.foodAPI.postRestaurantReview(token, reviewBody, restaurantID)
    }

    suspend fun searchFoodItems(token: String, query: String) : Response<FoodResponse> {
        return RetrofitInstance.foodAPI.searchFoodItems(token, query);
    }

    suspend fun searchRestaurants(token: String, query: String) : Response<RestaurantResponse> {
        return RetrofitInstance.foodAPI.searchRestaurants(token, query);
    }

}