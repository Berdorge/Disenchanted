package ru.disenchanted.backend.domain.university

interface UniversitySource {
    suspend fun getUniversityById(id: String): University?
    suspend fun getUniversitiesByIds(ids: List<String>): List<University>
    suspend fun getUniversities(): List<University>
}
