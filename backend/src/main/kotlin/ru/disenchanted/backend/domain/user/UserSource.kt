package ru.disenchanted.backend.domain.user

interface UserSource {
    suspend fun getUserByUsername(username: String): User?
    suspend fun insertUser(user: User)
}
