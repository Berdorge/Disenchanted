package ru.disenchanted.backend.domain.auth

import org.litote.kmongo.Id

interface JwtGenerator {
    fun generate(id: Id<*>): String
}
