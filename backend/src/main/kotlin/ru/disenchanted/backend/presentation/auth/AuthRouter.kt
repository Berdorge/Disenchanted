package ru.disenchanted.backend.presentation.auth

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import ru.disenchanted.backend.domain.auth.JwtGenerator
import ru.disenchanted.backend.domain.auth.PasswordGenerator
import ru.disenchanted.backend.domain.auth.PasswordValidator
import ru.disenchanted.backend.domain.auth.SignInRequest
import ru.disenchanted.backend.domain.auth.SignUpRequest
import ru.disenchanted.backend.domain.core.DefaultContext
import ru.disenchanted.backend.domain.user.User
import ru.disenchanted.backend.domain.user.UserSource
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import ru.disenchanted.backend.presentation.user.withJwt
import javax.inject.Inject

class AuthRouter @Inject constructor(
    private val application: Application,
    private val userSource: UserSource,
    private val passwordValidator: PasswordValidator,
    private val passwordGenerator: PasswordGenerator,
    private val jwtGenerator: JwtGenerator
) : ApplicationDelegate {
    override fun onApplicationReady() {
        application.routing {
            post("/signIn", ::signIn)
            post("/signUp", ::signUp)
        }
    }

    private suspend fun signIn(context: DefaultContext, request: SignInRequest) {
        val user = userSource.getUserByUsername(request.username)
        when {
            user == null -> context.call.respond(HttpStatusCode.NotFound)
            passwordValidator.validatePassword(request.password, user.password) -> {
                val jwt = jwtGenerator.generate(user.id)
                context.call.respond(user.withJwt(jwt))
            }
            else -> context.call.respond(HttpStatusCode.Unauthorized)
        }
    }

    private suspend fun signUp(context: DefaultContext, request: SignUpRequest) {
        val user = userSource.getUserByUsername(request.username)
        if (user == null) {
            val newUser = with(request) {
                User(
                    username = username,
                    password = passwordGenerator.generatePasswordHash(password)
                )
            }
            val jwt = jwtGenerator.generate(newUser.id)
            if (userSource.insertUser(newUser)) {
                context.call.respond(newUser.withJwt(jwt))
            } else {
                context.call.respond(HttpStatusCode.InsufficientStorage)
            }
        } else {
            context.call.respond(HttpStatusCode.Conflict)
        }
    }
}
