package io.github.ayonshafiul.foodreview.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ayonshafiul.foodreview.model.MsgResponse
import io.github.ayonshafiul.foodreview.model.Restaurant
import io.github.ayonshafiul.foodreview.model.Review
import io.github.ayonshafiul.foodreview.model.ReviewBody
import io.github.ayonshafiul.foodreview.repository.Repository
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel(val repository: Repository): ViewModel() {
    val _restaurant = MutableLiveData<Restaurant>()
    val restaurant : LiveData<Restaurant>
        get() = _restaurant

    val _restaurantResponse = MutableLiveData<MsgResponse>()
    val restaurantResponse: LiveData<MsgResponse>
        get() = _restaurantResponse

    val _reviews = MutableLiveData<List<Review>>()
    val reviews : LiveData<List<Review>>
        get() = _reviews


    fun getRestaurantDetails(token: String, restaurantID: Int) {
        viewModelScope.launch {
            try {
                val res = repository.getRestaurantDetails(token, restaurantID)
                if(res.isSuccessful) {
                    _restaurant.postValue(res.body()?.data?.get(0))
                } else {
                    Log.d("error", "getrestaurantDetails: " + "error occured")
                }
            } catch(excpetion: Exception) {
                Log.d("restaurantDetails", "getrestaurantDetails: " + excpetion.printStackTrace())
            }
        }
    }

    fun getRestaurantReviews(token: String, restaurantID: Int) {
        viewModelScope.launch {
            try {
                val res = repository.getRestaurantReviews(token, restaurantID)
                if(res.isSuccessful) {
                    _reviews.postValue(res.body()?.data)
                } else {
                    Log.d("error", "getRestaurantReviews: " + "error occured")
                }
            } catch(excpetion: Exception) {
                Log.d("restaurantDetails", "getRestaurantDetails: " + excpetion.printStackTrace())
            }
        }
    }

    fun postRestaurantReview(token: String, reviewBody: ReviewBody, restaurantID: Int){

        viewModelScope.launch {
            try {
                val res = repository.postRestaurantReview(token, reviewBody, restaurantID)
                if(res.isSuccessful) {
                    _restaurantResponse.postValue(res.body())

                } else {
                    Log.d("error", "getRestaurantReviews: " + "error occured")
                    Log.d("error", res.headers().toString() + res.code() + res.message())
                }
            } catch(excpetion: Exception) {
                Log.d("restaurantDetails", "getRestarrantDetails: " + excpetion.printStackTrace())
            }
        }


    }



}