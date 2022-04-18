package ru.disenchanted.backend.domain.activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.disenchanted.backend.domain.description.Description

@Serializable
data class DescribedActivity(
    @SerialName("_id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("logoUrl")
    val logoUrl: String?,

    @SerialName("descriptions")
    val descriptions: List<Description>
)
