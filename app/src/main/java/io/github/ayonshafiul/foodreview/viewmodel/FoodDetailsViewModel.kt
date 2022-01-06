package io.github.ayonshafiul.foodreview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ayonshafiul.foodreview.model.Food
import io.github.ayonshafiul.foodreview.model.MsgResponse
import io.github.ayonshafiul.foodreview.model.Review
import io.github.ayonshafiul.foodreview.model.ReviewBody
import io.github.ayonshafiul.foodreview.repository.Repository
import kotlinx.coroutines.launch

class FoodDetailsViewModel(val repository: Repository): ViewModel() {
    val _food = MutableLiveData<Food>()
    val food : LiveData<Food>
        get() = _food

    val _foodResponse = MutableLiveData<MsgResponse>()
    val foodResponse: LiveData<MsgResponse>
        get() = _foodResponse

    val _reviews = MutableLiveData<List<Review>>()
    val reviews : LiveData<List<Review>>
        get() = _reviews


    fun getFoodDetails(token: String, foodID: Int) {
        viewModelScope.launch {
            try {
                val res = repository.getFoodDetails(token, foodID)
                if(res.isSuccessful) {
                    _food.postValue(res.body()?.data?.get(0))
                } else {
                    Log.d("error", "getFoodDetails: " + "error occured")
                }
            } catch(excpetion: Exception) {
                Log.d("foodDetails", "getFoodDetails: " + excpetion.printStackTrace())
            }
        }
    }

    fun getFoodReviews(token: String, foodID: Int) {
        viewModelScope.launch {
            try {
                val res = repository.getFoodReviews(token, foodID)
                if(res.isSuccessful) {
                    _reviews.postValue(res.body()?.data)
                } else {
                    Log.d("error", "getFoodReviews: " + "error occured")
                }
            } catch(excpetion: Exception) {
                Log.d("foodDetails", "getFoodDetails: " + excpetion.printStackTrace())
            }
        }
    }

    fun postFoodReview(token: String, reviewBody: ReviewBody, foodID: Int){

        viewModelScope.launch {
            try {
                val res = repository.postFoodReview(token, reviewBody, foodID)
                if(res.isSuccessful) {
                    _foodResponse.postValue(res.body())

                } else {
                    Log.d("error", "getFoodReviews: " + "error occured")
                }
            } catch(excpetion: Exception) {
                Log.d("foodDetails", "getFoodDetails: " + excpetion.printStackTrace())
            }
        }


    }



}