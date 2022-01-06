package io.github.ayonshafiul.foodreview.model

data class Restaurant(
    val rating: Double,
    val ratingCount: Int,
    val ratingSum: Int,
    val restaurantDescription: String,
    val restaurantID: Int,
    val restaurantName: String
)