package ru.disenchanted.backend.data.auth

import io.ktor.application.Application
import ru.disenchanted.backend.domain.auth.JwtData
import ru.disenchanted.backend.domain.auth.JwtDataSource
import javax.inject.Inject

private const val JWT_PROPERTY_PREFIX = "jwt."
private const val SECRET_PROPERTY = "secret"
private const val ISSUER_PROPERTY = "issuer"
private const val AUDIENCE_PROPERTY = "audience"

class JwtDataSourceImpl @Inject constructor(private val application: Application) : JwtDataSource {
    override val jwtData: JwtData
        get() {
            val secret: String
            val issuer: String
            val audience: String
            with(application.environment.config) {
                secret = property(JWT_PROPERTY_PREFIX + SECRET_PROPERTY).getString()
                issuer = property(JWT_PROPERTY_PREFIX + ISSUER_PROPERTY).getString()
                audience = property(JWT_PROPERTY_PREFIX + AUDIENCE_PROPERTY).getString()
            }
            return JwtData(secret, issuer, audience)
        }
}
