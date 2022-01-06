package io.github.ayonshafiul.foodreview.utils

import io.github.ayonshafiul.foodreview.repository.Repository
import io.github.ayonshafiul.foodreview.viewmodel.*

class Instances {
    companion object {
        val repository  by lazy {
            Repository()
        }

        val authFactory: AuthViewModelFactory by lazy {
            AuthViewModelFactory(repository)
        }

        val splashFactory: SplashViewModelFactory by lazy {
            SplashViewModelFactory(repository)
        }

        val homeFactory: HomeViewModelFactory by lazy {
            HomeViewModelFactory(repository)
        }

        val foodDetailsFactory: FoodDetailsViewModelFactory by lazy {
            FoodDetailsViewModelFactory(repository)
        }

        val restauratDetailsFactory: RestaurantDetailsViewModelFactory by lazy {
            RestaurantDetailsViewModelFactory(repository)
        }
    }
}