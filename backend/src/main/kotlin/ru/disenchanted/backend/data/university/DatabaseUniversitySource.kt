package ru.disenchanted.backend.data.university

import kotlinx.coroutines.withContext
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.`in`
import ru.disenchanted.backend.domain.core.DispatchersProvider
import ru.disenchanted.backend.domain.university.University
import ru.disenchanted.backend.domain.university.UniversitySource
import javax.inject.Inject

class DatabaseUniversitySource @Inject constructor(
    private val collection: CoroutineCollection<University>,
    private val dispatchersProvider: DispatchersProvider
) : UniversitySource {
    override suspend fun getUniversities(): List<University> = withContext(dispatchersProvider.io) {
        collection.find().toList()
    }

    override suspend fun getUniversitiesByIds(
        ids: List<String>
    ): List<University> = withContext(dispatchersProvider.io) {
        collection.find(University::id `in` ids)
            .toList()
    }

    override suspend fun getUniversityById(
        id: String
    ): University? = withContext(dispatchersProvider.io) {
        collection.findOne(University::id eq id)
    }
}
