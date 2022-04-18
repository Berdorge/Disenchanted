package ru.disenchanted.backend.domain.program

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Program(
    @SerialName("_id")
    val id: String,

    @SerialName("name")
    val name: String
)
