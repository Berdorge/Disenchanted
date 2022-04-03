package ru.disenchanted.backend.data.user

import kotlinx.coroutines.withContext
import org.litote.kmongo.Id
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import ru.disenchanted.backend.domain.core.DispatchersProvider
import ru.disenchanted.backend.domain.user.User
import ru.disenchanted.backend.domain.user.UserSource
import javax.inject.Inject

class DatabaseUserSource @Inject constructor(
    private val collection: CoroutineCollection<User>,
    private val dispatchersProvider: DispatchersProvider
) : UserSource {
    override suspend fun getUserByUsername(
        username: String
    ): User? = withContext(dispatchersProvider.io) {
        collection.findOne(User::username eq username)
    }

    override suspend fun getUserById(id: Id<User>): User? = withContext(dispatchersProvider.io) {
        collection.findOne(User::id eq id)
    }

    override suspend fun insertUser(user: User) = withContext(dispatchersProvider.io) {
        collection.insertOne(user).wasAcknowledged()
    }
}
