package ru.disenchanted.backend.data.activity

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.litote.kmongo.Id

class UniversityProgramActivityId(serializedField: String) : Id<UniversityProgramActivityEntity> {
    private val field: UniversityProgramActivityIdField = Json.decodeFromString(serializedField)

    val activityId = field.activityId
    val universityId = field.universityId

    override fun toString(): String = Json.encodeToString(field)
}
