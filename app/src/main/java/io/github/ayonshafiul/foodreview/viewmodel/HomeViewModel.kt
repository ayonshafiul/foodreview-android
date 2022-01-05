package io.github.ayonshafiul.foodreview.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ayonshafiul.foodreview.model.Food
import io.github.ayonshafiul.foodreview.model.Restaurant
import io.github.ayonshafiul.foodreview.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(val repository: Repository) : ViewModel() {

    val topRestaurants = MutableLiveData<List<Restaurant>>()
    val popularRestaurants = MutableLiveData<List<Restaurant>>()
    val topFoodItems = MutableLiveData<List<Food>>()
    val popularFoodItems = MutableLiveData<List<Food>>()

    fun getTopRestaurants(token: String) {
        viewModelScope.launch {
            try {
                val res = repository.getTopRestaurants(token)
                if(res.isSuccessful) {
                    topRestaurants.postValue(res.body()?.data!!)
                } else {
                    Log.d("response", "getTopRestaurants: " + res.message())
                }
            } catch (e: Exception) {
                Log.d("error", "getTopRestaurants: " + e.printStackTrace())
            }

        }
    }

    fun getPopularRestaurants(token: String) {
        viewModelScope.launch {
            try {
                val res = repository.getPopularRestaurants(token)
                if(res.isSuccessful) {
                    popularRestaurants.postValue(res.body()?.data!!)
                } else {
                    Log.d("response", "getPopularRestaurants: " + res.message())
                }
            } catch (e: Exception) {
                Log.d("error", "getPopularRestaurants: " + e.printStackTrace())
            }

        }
    }

    fun getTopFoodItems(token: String) {
        viewModelScope.launch {
            try {
                val res = repository.getTopFoodItems(token)
                if(res.isSuccessful) {
                    topFoodItems.postValue(res.body()?.data!!)
                } else {
                    Log.d("response", "getTopFoodItems: " + res.message())
                }
            } catch (e: Exception) {
                Log.d("error", "getTopFoodItems: " + e.printStackTrace())
            }

        }
    }

    fun getPopularFoodItems(token: String) {
        viewModelScope.launch {
            try {
                val res = repository.getPopularFoodItems(token)
                if(res.isSuccessful) {
                    popularFoodItems.postValue(res.body()?.data!!)
                } else {
                    Log.d("response", "getPopularFoodItems " + res.message())
                }
            } catch (e: Exception) {
                Log.d("error", "getPopularFoodItems: " + e.printStackTrace())
            }

        }
    }
}