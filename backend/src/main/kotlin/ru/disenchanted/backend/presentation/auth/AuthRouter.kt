package ru.disenchanted.backend.presentation.auth

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.util.pipeline.PipelineContext
import ru.disenchanted.backend.domain.auth.PasswordGenerator
import ru.disenchanted.backend.domain.auth.PasswordValidator
import ru.disenchanted.backend.domain.auth.SignInRequest
import ru.disenchanted.backend.domain.auth.SignUpRequest
import ru.disenchanted.backend.domain.user.User
import ru.disenchanted.backend.domain.user.UserSource
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Inject

class AuthRouter @Inject constructor(
    private val application: Application,
    private val userSource: UserSource,
    private val passwordValidator: PasswordValidator,
    private val passwordGenerator: PasswordGenerator
) : ApplicationDelegate {
    override fun onApplicationReady() {
        application.routing {
            post("/signIn", ::signIn)
            post("/signUp", ::signUp)
        }
    }

    private suspend fun signIn(
        context: PipelineContext<Unit, ApplicationCall>,
        request: SignInRequest
    ) {
        val user = userSource.getUserByUsername(request.username)
        when {
            user == null -> context.call.respond(HttpStatusCode.NotFound)
            passwordValidator.validatePassword(request.password, user.password) ->
                context.call.respond(user)
            else -> context.call.respond(HttpStatusCode.Unauthorized)
        }
    }

    private suspend fun signUp(
        context: PipelineContext<Unit, ApplicationCall>,
        request: SignUpRequest
    ) {
        val user = userSource.getUserByUsername(request.username)
        if (user == null) {
            val newUser = with(request) {
                User(
                    username = username,
                    password = passwordGenerator.generatePasswordHash(password)
                )
            }
            userSource.insertUser(newUser)
            context.call.respond(newUser)
        } else {
            context.call.respond(HttpStatusCode.Conflict)
        }
    }
}
