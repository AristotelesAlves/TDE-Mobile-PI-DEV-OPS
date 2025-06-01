package com.example.pi_mobile.services

import com.example.pi_mobile.models.ListingResponseDTO
import retrofit2.Response
import retrofit2.http.GET



data class ListingsPageResponseDTO(
    val content: List<ListingResponseDTO>,
    val page: PageInfoDTO
)

data class PageInfoDTO(
    val size: Int,
    val number: Int,
    val totalElements: Int,
    val totalPages: Int
)


interface Listing {
    @GET("/listings")
    suspend fun listing(): Response<ListingsPageResponseDTO>

    @GET("/listings/{id}")
    suspend fun showOne(id: Int): Response<ListingResponseDTO>
}
