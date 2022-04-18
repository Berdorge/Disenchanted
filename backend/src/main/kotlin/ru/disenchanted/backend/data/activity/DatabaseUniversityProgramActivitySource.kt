package ru.disenchanted.backend.data.activity

import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.group
import org.litote.kmongo.match
import ru.disenchanted.backend.domain.activity.Activity
import ru.disenchanted.backend.domain.activity.ActivitySource
import ru.disenchanted.backend.domain.activity.UniversityProgramActivitySource
import ru.disenchanted.backend.domain.core.DispatchersProvider
import ru.disenchanted.backend.domain.university.University
import ru.disenchanted.backend.domain.university.UniversitySource
import javax.inject.Inject

class DatabaseUniversityProgramActivitySource @Inject constructor(
    private val collection: CoroutineCollection<UniversityProgramActivityEntity>,
    private val universitySource: UniversitySource,
    private val activitySource: ActivitySource,
    private val dispatchersProvider: DispatchersProvider
) : UniversityProgramActivitySource {
    override suspend fun getActivityUniversities(activityId: String): List<University> {
        val universityPrograms = getActivityUniversityProgramsEntities(activityId)
        val universitiesIds = withContext(dispatchersProvider.default) {
            universityPrograms.map { it.universityId }
        }
        return universitySource.getUniversitiesByIds(universitiesIds)
    }

    override suspend fun getUniversityActivities(
        universityId: String
    ): List<Activity> = getUniversityProgramActivities(universityId, null)

    override suspend fun getUniversityProgramActivities(
        universityId: String,
        programId: String?
    ): List<Activity> {
        val activities = getUniversityProgramActivitiesEntities(universityId, programId)
        val activitiesIds = withContext(dispatchersProvider.default) {
            activities.map { it.id.activityId }
        }
        return activitySource.getActivitiesByIds(activitiesIds)
    }

    private suspend fun getActivityUniversityProgramsEntities(
        activityId: String
    ): List<UniversityAggregation> = withContext(dispatchersProvider.io) {
        collection.aggregate<UniversityAggregation>(
            match(
                UniversityProgramActivityEntity::id /
                    UniversityProgramActivityIdField::activityId eq
                    activityId
            ),
            group(
                UniversityProgramActivityEntity::id / UniversityProgramActivityIdField::universityId
            )
        )
            .toList()
    }

    private suspend fun getUniversityProgramActivitiesEntities(
        universityId: String,
        programId: String?
    ): List<UniversityProgramActivityEntity> = withContext(dispatchersProvider.io) {
        val query = and(
            UniversityProgramActivityEntity::id /
                UniversityProgramActivityIdField::universityId eq
                universityId,
            UniversityProgramActivityEntity::id /
                UniversityProgramActivityIdField::programId eq
                programId
        )
        collection.find(query)
            .toList()
    }

    @Serializable
    private data class UniversityAggregation(
        @Serializable
        @SerialName("_id")
        val universityId: String
    )
}
