package io.github.ayonshafiul.foodreview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.ayonshafiul.foodreview.repository.Repository

class FoodDetailsViewModelFactory(val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodDetailsViewModel(repository) as T
    }
}