package ru.disenchanted.backend.domain.activity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    @SerialName("_id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("parentId")
    val parentId: String?
)
