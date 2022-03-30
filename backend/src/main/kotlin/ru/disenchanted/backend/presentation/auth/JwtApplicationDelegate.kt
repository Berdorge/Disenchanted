package ru.disenchanted.backend.presentation.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import ru.disenchanted.backend.domain.auth.JwtDataSource
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Inject

class JwtApplicationDelegate @Inject constructor(
    private val application: Application,
    private val jwtDataSource: JwtDataSource,
    private val jwtValidator: JwtValidator
) : ApplicationDelegate {
    override fun onApplicationReady() {
        val (secret, issuer, audience) = jwtDataSource.jwtData
        application.install(Authentication) {
            jwt {
                val verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
                verifier(verifier)
                validate { jwtValidator.validate(it) }
            }
        }
    }
}
