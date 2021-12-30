package io.github.ayonshafiul.foodreview.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.model.TokenResponse
import io.github.ayonshafiul.foodreview.model.User
import io.github.ayonshafiul.foodreview.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(val repository: Repository) : ViewModel() {

    val tokenResponse: MutableLiveData<TokenResponse> = MutableLiveData()
    fun login(user: User) {
        viewModelScope.launch {
            val res = repository.login(user)
            if(res.isSuccessful) {
                Log.d("response", "login: " + res.body())
                tokenResponse.postValue(res.body());

            } else {
                Log.d("response", "login  "+ res.errorBody())
            }
        }
    }
}