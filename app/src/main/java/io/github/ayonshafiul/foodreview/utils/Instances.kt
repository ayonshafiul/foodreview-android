package io.github.ayonshafiul.foodreview.utils

import io.github.ayonshafiul.foodreview.repository.Repository
import io.github.ayonshafiul.foodreview.viewmodel.AuthViewModel
import io.github.ayonshafiul.foodreview.viewmodel.AuthViewModelFactory
import io.github.ayonshafiul.foodreview.viewmodel.HomeViewModelFactory
import io.github.ayonshafiul.foodreview.viewmodel.SplashViewModelFactory

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
    }
}