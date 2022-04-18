package ru.disenchanted.backend.domain.university

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.disenchanted.backend.domain.program.Program

@Serializable
data class UniversityProgram(
    @SerialName("university")
    val university: University,

    @SerialName("program")
    val program: Program
)
