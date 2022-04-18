package ru.disenchanted.backend.data.university

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityProgramEntity(
    @Contextual
    @SerialName("_id")
    val id: UniversityProgramId
)
