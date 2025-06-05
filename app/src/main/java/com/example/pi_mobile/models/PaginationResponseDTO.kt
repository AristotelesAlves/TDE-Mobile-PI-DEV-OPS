package com.example.pi_mobile.models

data class PaginationResponseDTO<T>(
    val content: List<T>,
    val page: PaginationPageResponseDTO
)
