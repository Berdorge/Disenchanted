package ru.disenchanted.backend.presentation.auth

import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal
import ru.disenchanted.backend.domain.auth.JwtPayload

interface JwtValidator {
    fun validate(credential: JWTCredential): JWTPrincipal?
    fun retrievePayload(jwtPrincipal: JWTPrincipal): JwtPayload
}
