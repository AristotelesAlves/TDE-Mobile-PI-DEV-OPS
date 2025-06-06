package com.example.pi_mobile.models

data class ListingResponseDTO(
    val id: Long,
    val title: String,
    val price: Float,
    val description: String,
    val location: String,
    val creationDate: String,
    val userProfile: ListingUserProfileResponseDTO,
    val skills: List<String>
)

