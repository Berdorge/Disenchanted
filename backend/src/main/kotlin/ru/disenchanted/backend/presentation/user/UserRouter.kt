package ru.disenchanted.backend.presentation.user

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import ru.disenchanted.backend.domain.auth.JwtPayload
import ru.disenchanted.backend.domain.core.DefaultContext
import ru.disenchanted.backend.domain.user.UserSource
import ru.disenchanted.backend.domain.user.asExposable
import ru.disenchanted.backend.presentation.auth.AUTH_NAME
import ru.disenchanted.backend.presentation.auth.JwtValidator
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Inject

private const val USERNAME = "username"

class UserRouter @Inject constructor(
    private val application: Application,
    private val jwtValidator: JwtValidator,
    private val userSource: UserSource
) : ApplicationDelegate {
    override fun onApplicationReady() {
        application.routing {
            authenticate(AUTH_NAME) {
                get("/users/me") {
                    getMe(this)
                }
            }
            get("/users/{$USERNAME}") {
                val parameter = call.parameters[USERNAME]
                if (parameter == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    getUser(this, parameter)
                }
            }
        }
    }

    private suspend fun getMe(context: DefaultContext) {
        val principal = context.call.principal<JWTPrincipal>()

        if (principal == null) {
            context.call.respond(HttpStatusCode.Unauthorized)
        } else {
            getMeFromPayload(context, jwtValidator.retrievePayload(principal))
        }
    }

    private suspend fun getUser(context: DefaultContext, username: String) {
        val user = userSource.getUserByUsername(username)
        if (user == null) {
            context.call.respond(HttpStatusCode.NotFound)
        } else {
            context.call.respond(user.asExposable())
        }
    }

    private suspend fun getMeFromPayload(context: DefaultContext, payload: JwtPayload) {
        val user = userSource.getUserById(payload.id)
        if (user == null) {
            context.call.respond(HttpStatusCode.Unauthorized)
        } else {
            context.call.respond(user.asExposable())
        }
    }
}
