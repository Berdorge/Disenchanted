package ru.disenchanted.backend.domain.activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChildActivity(
    @SerialName("_id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("logoUrl")
    val logoUrl: String?,

    @SerialName("parent")
    val parent: Activity?
)
