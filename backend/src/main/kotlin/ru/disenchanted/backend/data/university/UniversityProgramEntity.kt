package ru.disenchanted.backend.data.university

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityProgramEntity(
    @SerialName("_id")
    val id: UniversityProgramId
)
