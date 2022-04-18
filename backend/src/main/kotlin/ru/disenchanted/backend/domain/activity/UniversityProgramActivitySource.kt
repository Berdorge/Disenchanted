package ru.disenchanted.backend.domain.activity

import ru.disenchanted.backend.domain.university.University

interface UniversityProgramActivitySource {
    suspend fun getActivityUniversities(activityId: String): List<University>
    suspend fun getUniversityActivities(universityId: String): List<DescribedActivity>
    suspend fun getUniversityProgramActivities(
        universityId: String,
        programId: String?
    ): List<DescribedActivity>
}
