package io.github.ayonshafiul.foodreview.model

data class Food(
    val foodDescription: String,
    val foodID: Int,
    val foodName: String,
    val foodPrice: Int,
    val rating: Double,
    val ratingCount: Int,
    val ratingSum: Int,
    val restaurantID: Int
)