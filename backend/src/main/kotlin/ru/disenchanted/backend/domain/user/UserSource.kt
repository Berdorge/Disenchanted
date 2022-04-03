package ru.disenchanted.backend.domain.user

import org.litote.kmongo.Id

interface UserSource {
    suspend fun getUserByUsername(username: String): User?
    suspend fun getUserById(id: Id<User>): User?
    suspend fun insertUser(user: User): Boolean
}
