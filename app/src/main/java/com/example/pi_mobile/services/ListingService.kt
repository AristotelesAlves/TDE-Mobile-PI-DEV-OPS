package com.example.pi_mobile.services

import com.example.pi_mobile.models.ListingResponseDTO
import com.example.pi_mobile.models.PaginationResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListingService {

   // desculpa novamente, estou sem tempo !

    data class ListingResponsev2DTO(
        val id: Long,
        val title: String,
        val price: Double,
        val description: String,
        val location: String,
        val creationDate: String,
        val userProfile: UserProfileDTO,
        val skills: List<String>,
        val contractedListings: List<ContractedListingDTO>
    )

    data class UserProfileDTO(
        val id: Long,
        val name: String,
        val description: String,
        val title: String
    )

    data class ContractedListingDTO(
        val id: Long,
        val status: String,
        val clientRequest: String,
        val startedAt: String?,
        val finishedAt: String?,
        val listing: Any?,
        val client: ClientDTO,
        val evaluation: EvaluationDTO?
    )

    data class ClientDTO(
        val id: Long,
        val name: String,
        val phone: String,
        val address: String,
        val postalCode: String,
        val skills: List<String>,
        val document: String,
        val description: String,
        val title: String
    )

    data class EvaluationDTO(
        val id: Long,
        val comment: String,
        val stars: Int,
        val contractedListingId: Long
    )


    @GET("/listings")
    suspend fun findAll(): Response<PaginationResponseDTO<ListingResponseDTO>>

    @GET("/listings/{id}")
    suspend fun findById(@Path("id") id: Long): Response<ListingResponsev2DTO>

    // Desculpa, vou fazer assum quick and dart, estou sem tempo :(), nem sei se deu cero :()

    @GET("/listings")
    suspend fun findAllByAccountIdProfile(
        @Query("size") size: Int = 3,
        @Query("sortBy") sortBy: String = "creationDate",
        @Query("accountId") accountId: Int
    ): Response<PaginationResponseDTO<ListingResponseDTO>>

}
