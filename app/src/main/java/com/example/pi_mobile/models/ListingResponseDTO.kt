package com.example.pi_mobile.models

import java.time.LocalDate

data class ListingResponseDTO(
    val id: Long,
    val title: String,
    val price: Float,
    val description: String,
    val location: String,
    val creationDate: LocalDate,
    val userProfile: ListingUserProfileResponseDTO,
    val skills: List<String>,
    //Caso dê erro aqui talvez seja pq não adicionei a lista de ContractedListingResponseDTO
)
