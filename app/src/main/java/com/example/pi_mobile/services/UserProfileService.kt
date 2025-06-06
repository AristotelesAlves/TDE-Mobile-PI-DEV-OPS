package com.example.pi_mobile.services

import com.example.pi_mobile.models.PaginationResponseDTO
import com.example.pi_mobile.models.UserProfileRequestDTO
import com.example.pi_mobile.models.UserProfileResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserProfileService {
    @GET("/user-profiles")
    suspend fun findAll(): Response<PaginationResponseDTO<UserProfileResponseDTO>>

    @GET("/user-profiles/{id}")
    suspend fun findById(id: Long): Response<UserProfileResponseDTO>

    @POST("/user-profiles")
    suspend fun create(@Body userProfile: UserProfileRequestDTO): Response<UserProfileResponseDTO>

    @PUT("/user-profiles/{id}")
    suspend fun update(@Body userProfile: UserProfileRequestDTO, id: Long): Response<UserProfileResponseDTO>
}
