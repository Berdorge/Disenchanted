package ru.disenchanted.backend.data.program

import kotlinx.coroutines.withContext
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.`in`
import ru.disenchanted.backend.domain.core.DispatchersProvider
import ru.disenchanted.backend.domain.program.Program
import ru.disenchanted.backend.domain.program.ProgramSource
import javax.inject.Inject

class DatabaseProgramSource @Inject constructor(
    private val collection: CoroutineCollection<Program>,
    private val dispatchersProvider: DispatchersProvider
) : ProgramSource {
    override suspend fun getProgramById(
        id: String
    ): Program? = withContext(dispatchersProvider.io) {
        collection.findOne(Program::id eq id)
    }

    override suspend fun getProgramsByIds(
        ids: List<String>
    ): List<Program> = withContext(dispatchersProvider.io) {
        collection.find(Program::id `in` ids)
            .toList()
    }
}
