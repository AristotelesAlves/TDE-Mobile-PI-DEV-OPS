package com.example.pi_mobile.services

import com.example.pi_mobile.models.UserRequestDTO
import com.example.pi_mobile.models.UserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: UserRequestDTO): Response<UserResponseDTO>

    @POST("auth/register")
    suspend fun register(@Body request: UserRequestDTO): Response<UserResponseDTO>
}
