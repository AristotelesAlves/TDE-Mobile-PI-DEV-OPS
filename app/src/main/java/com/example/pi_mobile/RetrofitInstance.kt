package com.example.pi_mobile


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/01001000/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PokeApiService by lazy {
        val response = retrofit.create(PokeApiService::class.java)
        println(response)
        response
    }
}
