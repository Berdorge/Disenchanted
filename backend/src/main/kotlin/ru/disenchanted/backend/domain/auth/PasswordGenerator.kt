package ru.disenchanted.backend.domain.auth

interface PasswordGenerator {
    suspend fun generatePasswordHash(password: String): String
}
