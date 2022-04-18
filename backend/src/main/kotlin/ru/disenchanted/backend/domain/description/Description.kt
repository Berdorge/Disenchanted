package ru.disenchanted.backend.domain.description

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Description(
    @SerialName("name")
    val name: String,

    @SerialName("content")
    val content: String
)
