package com.example.pi_mobile.services

import com.example.pi_mobile.services.interceptors.JwtInterceptor
import com.example.pi_mobile.utils.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{

    val UserProfileService: UserProfileService by lazy {
        retrofit.create(UserProfileService::class.java)
    }

    val listingService: ListingService by lazy {
        retrofit.create(ListingService::class.java)
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val interceptor: JwtInterceptor by lazy {
        JwtInterceptor(SessionManager.jwtToken ?: "")
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            )
            .build()
    }
}
