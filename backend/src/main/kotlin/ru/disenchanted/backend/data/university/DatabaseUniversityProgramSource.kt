package ru.disenchanted.backend.data.university

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.div
import org.litote.kmongo.eq
import ru.disenchanted.backend.domain.core.DispatchersProvider
import ru.disenchanted.backend.domain.program.Program
import ru.disenchanted.backend.domain.program.ProgramSource
import ru.disenchanted.backend.domain.university.UniversityProgram
import ru.disenchanted.backend.domain.university.UniversityProgramSource
import ru.disenchanted.backend.domain.university.UniversitySource
import javax.inject.Inject

class DatabaseUniversityProgramSource @Inject constructor(
    private val collection: CoroutineCollection<UniversityProgramEntity>,
    private val programSource: ProgramSource,
    private val universitySource: UniversitySource,
    private val dispatchersProvider: DispatchersProvider
) : UniversityProgramSource {
    override suspend fun getAllOfUniversityPrograms(
        universityId: String
    ): List<Program> {
        val programsIds = withContext(dispatchersProvider.default) {
            getUniversityPrograms(universityId).map {
                it.id.programId
            }
        }
        return programSource.getProgramsByIds(programsIds)
    }

    override suspend fun getUniversityProgramById(
        universityId: String,
        programId: String
    ): UniversityProgram? {
        val queryId = UniversityProgramId(universityId, programId)
        val entity = withContext(dispatchersProvider.io) {
            collection.findOne(UniversityProgramEntity::id eq queryId)
        }
        return if (entity == null) {
            null
        } else {
            getUniversityProgramByIdWithoutChecking(universityId, programId)
        }
    }

    private suspend fun getUniversityPrograms(
        universityId: String
    ) = withContext(dispatchersProvider.io) {
        collection.find(
            UniversityProgramEntity::id /
                UniversityProgramId::universityId eq
                universityId
        )
            .toList()
    }

    private suspend fun getUniversityProgramByIdWithoutChecking(
        universityId: String,
        programId: String
    ): UniversityProgram? = coroutineScope {
        val universityDeferred = async(dispatchersProvider.io) {
            universitySource.getUniversityById(universityId)
        }
        val programDeferred = async(dispatchersProvider.io) {
            programSource.getProgramById(programId)
        }
        val university = universityDeferred.await()
        val program = programDeferred.await()
        if (university == null || program == null) {
            null
        } else {
            UniversityProgram(university, program)
        }
    }
}
