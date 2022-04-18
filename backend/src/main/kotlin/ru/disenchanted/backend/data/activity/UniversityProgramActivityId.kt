package ru.disenchanted.backend.data.activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityProgramActivityId(
    @SerialName("universityId")
    val universityId: String,

    @SerialName("programId")
    val programId: String?,

    @SerialName("activityId")
    val activityId: String
)
