package ru.disenchanted.backend.data.auth

import kotlinx.coroutines.withContext
import org.mindrot.jbcrypt.BCrypt
import ru.disenchanted.backend.domain.auth.PasswordGenerator
import ru.disenchanted.backend.domain.auth.PasswordValidator
import ru.disenchanted.backend.domain.core.DispatchersProvider
import javax.inject.Inject

class PasswordManager @Inject constructor(
    private val dispatchersProvider: DispatchersProvider
) : PasswordValidator, PasswordGenerator {
    override suspend fun validatePassword(
        password: String,
        hash: String
    ): Boolean = withContext(dispatchersProvider.default) {
        BCrypt.checkpw(password, hash)
    }

    override suspend fun generatePasswordHash(
        password: String
    ): String = withContext(dispatchersProvider.default) {
        BCrypt.hashpw(password, BCrypt.gensalt())
    }
}
