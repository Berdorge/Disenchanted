package ru.disenchanted.backend.data.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal
import ru.disenchanted.backend.domain.auth.JwtDataSource
import ru.disenchanted.backend.domain.auth.JwtGenerator
import ru.disenchanted.backend.domain.core.DispatchersProvider
import ru.disenchanted.backend.presentation.auth.JwtValidator
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val USERNAME = "username"
private const val JWT_EXPIRY_PERIOD_IN_DAYS = 14L

class JwtManager @Inject constructor(
    private val jwtDataSource: JwtDataSource,
    private val dispatchersProvider: DispatchersProvider
) : JwtValidator, JwtGenerator {
    private val expiryPeriodMillis = TimeUnit.DAYS.toMillis(JWT_EXPIRY_PERIOD_IN_DAYS)

    override fun validate(credential: JWTCredential): JWTPrincipal? {
        val username = credential[USERNAME]
        return if (username.isNullOrEmpty()) {
            null
        } else {
            JWTPrincipal(credential.payload)
        }
    }

    override fun generate(username: String): String = with(dispatchersProvider.default) {
        val expirationDate = Date(System.currentTimeMillis() + expiryPeriodMillis)
        val (secret, issuer, audience) = jwtDataSource.jwtData
        JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim(USERNAME, username)
            .withExpiresAt(expirationDate)
            .sign(Algorithm.HMAC256(secret))
    }
}
