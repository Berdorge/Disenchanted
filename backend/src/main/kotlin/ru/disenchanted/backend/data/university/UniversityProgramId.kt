package ru.disenchanted.backend.data.university

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.litote.kmongo.Id
import ru.disenchanted.backend.domain.university.UniversityProgram

class UniversityProgramId : Id<UniversityProgram> {
    private val field: UniversityProgramIdField

    val programId
        get() = this.field.programId

    constructor(serializedField: String) {
        field = Json.decodeFromString(serializedField)
    }

    constructor(field: UniversityProgramIdField) {
        this.field = field
    }

    override fun toString(): String = Json.encodeToString(field)
}
