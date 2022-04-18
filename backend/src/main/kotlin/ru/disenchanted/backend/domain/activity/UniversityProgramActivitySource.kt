package ru.disenchanted.backend.domain.activity

import ru.disenchanted.backend.domain.university.University

interface UniversityProgramActivitySource {
    suspend fun getActivityUniversities(activityId: String): List<University>
    suspend fun getUniversityActivities(universityId: String): List<Activity>
    suspend fun getUniversityProgramActivities(
        universityId: String,
        programId: String?
    ): List<Activity>
}
