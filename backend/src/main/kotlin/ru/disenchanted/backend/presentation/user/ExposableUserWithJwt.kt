package ru.disenchanted.backend.presentation.user

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import ru.disenchanted.backend.domain.user.User

@Serializable
data class ExposableUserWithJwt(
    @SerialName("_id")
    @Contextual
    val id: Id<User>,

    @SerialName("username")
    val username: String,

    @SerialName("jwt")
    val jwt: String
)
