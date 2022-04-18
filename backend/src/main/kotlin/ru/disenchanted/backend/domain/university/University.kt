package ru.disenchanted.backend.domain.university

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class University(
    @SerialName("_id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("logoUrl")
    val logoUrl: String
)
