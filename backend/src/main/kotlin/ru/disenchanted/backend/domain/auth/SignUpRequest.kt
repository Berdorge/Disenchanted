package ru.disenchanted.backend.domain.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("username")
    val username: String,

    @SerialName("password")
    val password: String
)
