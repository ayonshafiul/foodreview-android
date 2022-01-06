package io.github.ayonshafiul.foodreview.model

data class ReviewResponse(
    val data: List<Review>,
    val success: Boolean
)