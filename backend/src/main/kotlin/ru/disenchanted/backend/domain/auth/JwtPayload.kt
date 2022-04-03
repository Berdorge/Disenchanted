package ru.disenchanted.backend.domain.auth

import org.litote.kmongo.Id
import ru.disenchanted.backend.domain.user.User

data class JwtPayload(
    val id: Id<User>
)
