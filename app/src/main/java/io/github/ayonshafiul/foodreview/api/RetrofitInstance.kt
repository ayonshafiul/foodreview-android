package io.github.ayonshafiul.foodreview.api

import com.google.gson.GsonBuilder
import io.github.ayonshafiul.foodreview.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val client = OkHttpClient.Builder().addInterceptor(FoodAPIInterceptor()).build()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                .setLenient()
                .create()))
            .build()
    }
    val foodAPI: FoodAPI by lazy {
        retrofit.create(FoodAPI::class.java)
    }
}