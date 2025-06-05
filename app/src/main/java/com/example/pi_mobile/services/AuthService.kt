package com.example.pi_mobile.services

import com.example.pi_mobile.models.UserRequestDTO
import com.example.pi_mobile.models.UserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @GET("auth/login")
    suspend fun login(@Header("authorization") authorization: String): Response<UserResponseDTO>

    @POST("auth/register")
    suspend fun register(@Body userRequestDTO: UserRequestDTO): Response<UserResponseDTO>
}
