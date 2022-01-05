package io.github.ayonshafiul.foodreview.model

data class FoodResponse(
    val `data`: List<Food>,
    val success: Boolean
)