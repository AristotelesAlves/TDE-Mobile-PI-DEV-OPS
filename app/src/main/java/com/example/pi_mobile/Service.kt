package com.example.pi_mobile

import retrofit2.http.GET
import retrofit2.http.Path

data class responseViaCep(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val unidade: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val estado: String,
    val regiao: String,
    val ibge: String,
    val gia: String,
    val ddd: String,
    val siafi: String
)

interface Service {
    @GET("{cep}/json/")
    suspend fun getCep(@Path("cep") cep: String): responseViaCep
}
