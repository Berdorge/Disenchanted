package ru.disenchanted.backend.domain.auth

data class JwtData(
    val secret: String,
    val issuer: String,
    val audience: String
)
