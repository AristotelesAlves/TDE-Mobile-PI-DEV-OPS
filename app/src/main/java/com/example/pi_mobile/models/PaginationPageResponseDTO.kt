package com.example.pi_mobile.models

data class PaginationPageResponseDTO(
    val size: Int,
    val number: Int,
    val totalElements: Int,
    val totalPages: Int,
)
