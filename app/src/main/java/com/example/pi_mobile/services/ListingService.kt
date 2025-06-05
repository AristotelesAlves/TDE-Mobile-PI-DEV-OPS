package com.example.pi_mobile.services

import com.example.pi_mobile.models.ListingResponseDTO
import com.example.pi_mobile.models.PaginationResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface ListingService {
    @GET("/listings")
    suspend fun findAll(): Response<PaginationResponseDTO<ListingResponseDTO>>

    @GET("/listings/{id}")
    suspend fun findById(id: Int): Response<ListingResponseDTO>
}
