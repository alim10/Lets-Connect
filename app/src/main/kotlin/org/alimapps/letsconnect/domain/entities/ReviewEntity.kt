package org.alimapps.letsconnect.app_reviews.domain.entities

data class ReviewEntity(
    val id: String,
    val image: String,
    val name: String,
    val desc: String,
    val date: String,
    val rating: Float,
) {

}
