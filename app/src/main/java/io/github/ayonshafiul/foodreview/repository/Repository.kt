package io.github.ayonshafiul.foodreview.repository

import io.github.ayonshafiul.foodreview.api.RetrofitInstance
import io.github.ayonshafiul.foodreview.model.MsgResponse
import io.github.ayonshafiul.foodreview.model.TokenResponse
import io.github.ayonshafiul.foodreview.model.User
import retrofit2.Response

class Repository {
    suspend fun login(user : User): Response<TokenResponse> {
        return RetrofitInstance.foodAPI.login(user)
    }

    suspend fun checkAuthenticated(token: String): Response<MsgResponse> {
        return RetrofitInstance.foodAPI.checkAuthenticated(token)
    }
}