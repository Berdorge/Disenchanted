package ru.disenchanted.backend.domain.auth

interface PasswordValidator {
    suspend fun validatePassword(password: String, hash: String): Boolean
}
