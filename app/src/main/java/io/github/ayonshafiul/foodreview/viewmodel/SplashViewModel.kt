package io.github.ayonshafiul.foodreview.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ayonshafiul.foodreview.model.MsgResponse
import io.github.ayonshafiul.foodreview.repository.Repository
import kotlinx.coroutines.launch

class SplashViewModel(val repository: Repository) : ViewModel() {
    val msgResponse = MutableLiveData<MsgResponse>()

    fun checkAuthenticated(token: String) {
        viewModelScope.launch {
            val res = repository.checkAuthenticated(token)
            if(res.isSuccessful) {
                msgResponse.postValue(res.body())
            } else {
                Log.d("Response", "checkAuthenticated: " + res.errorBody() + res.code() + res.message() + res.raw())
            }
        }
    }
}