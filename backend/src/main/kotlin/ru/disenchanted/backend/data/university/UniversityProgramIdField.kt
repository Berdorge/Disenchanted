package ru.disenchanted.backend.data.university

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityProgramIdField(
    @SerialName("universityId")
    val universityId: String,

    @SerialName("programId")
    val programId: String
)
