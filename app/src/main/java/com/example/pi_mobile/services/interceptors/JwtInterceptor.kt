package com.example.pi_mobile.services.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class JwtInterceptor(var token: String) : Interceptor {
    private val noAuthPaths = listOf("/auth/login", "/auth/register", "/auth/refresh-token")

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val path = originalRequest.url().encodedPath()

        val request = if (noAuthPaths.any { path.endsWith(it) } || token.isEmpty()) {
            Log.d("JwtInterceptor", "No auth required for path: $path")
            originalRequest
        } else {
            Log.d("JwtInterceptor", "Auth used: $path")
            originalRequest.newBuilder()
                .addHeader("Authorization", token)
                .build()
        }
        return chain.proceed(request)
    }
}