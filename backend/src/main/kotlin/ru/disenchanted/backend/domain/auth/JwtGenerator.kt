package ru.disenchanted.backend.domain.auth

interface JwtGenerator {
    fun generate(username: String): String
}
