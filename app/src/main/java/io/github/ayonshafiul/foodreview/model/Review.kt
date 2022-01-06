package io.github.ayonshafiul.foodreview.model

data class Review(
    val rating: Double,
    val restaurantID: Int,
    val review: String,
    val userID: Int
)