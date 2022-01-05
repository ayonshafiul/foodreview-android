package io.github.ayonshafiul.foodreview.model

data class RestaurantResponse(
    val data : List<Restaurant>?,
    val success: Boolean
)