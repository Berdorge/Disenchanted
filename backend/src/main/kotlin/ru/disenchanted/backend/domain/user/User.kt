package ru.disenchanted.backend.domain.user

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class User(
    @Contextual
    @SerialName("_id")
    val id: Id<User> = newId(),

    @SerialName("username")
    val username: String,

    @SerialName("password")
    val password: String
)
