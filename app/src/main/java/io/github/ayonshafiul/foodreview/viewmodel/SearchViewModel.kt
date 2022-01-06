package io.github.ayonshafiul.foodreview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ayonshafiul.foodreview.model.Food
import io.github.ayonshafiul.foodreview.model.Restaurant
import io.github.ayonshafiul.foodreview.repository.Repository
import kotlinx.coroutines.launch

class SearchViewModel(val repository: Repository) : ViewModel(){
    val _restaurants = MutableLiveData<List<Restaurant>>()

    val restaurants : LiveData<List<Restaurant>>
            get() = _restaurants

    val _foodItems = MutableLiveData<List<Food>>()

    val foodItems : LiveData<List<Food>>
        get() = _foodItems


    fun searchFoodItems(token: String, query: String) {
        viewModelScope.launch {
            try {
                val res = repository.searchFoodItems(token, query)
                if(res.isSuccessful) {
                    _foodItems.postValue(res.body()?.data!!)
                } else {
                    Log.d("response", "getTopFoodItems: " + res.message())
                }
            } catch (e: Exception) {
                Log.d("error", "getTopFoodItems: " + e.printStackTrace())
            }

        }
    }

    fun searchRestaurants(token: String, query: String) {
        viewModelScope.launch {
            try {
                val res = repository.searchRestaurants(token, query)
                if(res.isSuccessful) {
                    _restaurants.postValue(res.body()?.data!!)
                } else {
                    Log.d("response", "getTopFoodItems: " + res.message())
                }
            } catch (e: Exception) {
                Log.d("error", "getTopFoodItems: " + e.printStackTrace())
            }
        }
    }

}