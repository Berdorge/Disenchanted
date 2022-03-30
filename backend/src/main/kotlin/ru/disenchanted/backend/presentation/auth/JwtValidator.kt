package ru.disenchanted.backend.presentation.auth

import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal

interface JwtValidator {
    fun validate(credential: JWTCredential): JWTPrincipal?
}
