package ru.disenchanted.backend.domain.university

import ru.disenchanted.backend.domain.program.Program

interface UniversityProgramSource {
    suspend fun getAllOfUniversityPrograms(universityId: String): List<Program>
    suspend fun getUniversityProgramById(
        universityId: String,
        programId: String
    ): UniversityProgram?
}
