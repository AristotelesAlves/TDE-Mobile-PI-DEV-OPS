package com.example.pi_mobile.models

data class UserProfileRequestDTO(
    val name: String,
    val phone: String,
    val address: String,
    val postalCode: String,
    val skills: Set<String>,
    val document: String,
    val description: String,
    val title: String
)
