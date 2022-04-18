package ru.disenchanted.backend.data.activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.disenchanted.backend.domain.description.Description

@Serializable
data class UniversityProgramActivityEntity(
    @SerialName("_id")
    val id: UniversityProgramActivityId,

    @SerialName("descriptions")
    val descriptions: List<Description>
)
