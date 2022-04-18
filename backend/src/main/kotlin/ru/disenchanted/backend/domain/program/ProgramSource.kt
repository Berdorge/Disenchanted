package ru.disenchanted.backend.domain.program

interface ProgramSource {
    suspend fun getProgramById(id: String): Program?
    suspend fun getProgramsByIds(ids: List<String>): List<Program>
}
