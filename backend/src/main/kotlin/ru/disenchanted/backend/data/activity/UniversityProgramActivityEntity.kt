package ru.disenchanted.backend.data.activity

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityProgramActivityEntity(
    @SerialName("_id")
    @Contextual
    val id: UniversityProgramActivityId
)
