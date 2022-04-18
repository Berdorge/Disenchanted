package ru.disenchanted.backend.presentation.university

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import ru.disenchanted.backend.domain.core.DefaultContext
import ru.disenchanted.backend.domain.university.UniversityProgramSource
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Inject

private const val UNIVERSITY_ID = "university_id"
private const val UNIVERSITY_PROGRAM_ID = "program_id"

class UniversityProgramRouter @Inject constructor(
    private val application: Application,
    private val universityProgramSource: UniversityProgramSource
) : ApplicationDelegate {
    override fun onApplicationReady() {
        application.routing {
            get("universities/{$UNIVERSITY_ID}/programs") {
                val universityId = call.parameters[UNIVERSITY_ID]
                if (universityId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    getUniversityPrograms(this, universityId)
                }
            }
            get("universities/{$UNIVERSITY_ID}/programs/{$UNIVERSITY_PROGRAM_ID}") {
                val universityId = call.parameters[UNIVERSITY_ID]
                val programId = call.parameters[UNIVERSITY_PROGRAM_ID]
                if (universityId == null || programId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    getUniversityProgram(this, universityId, programId)
                }
            }
        }
    }

    private suspend fun getUniversityPrograms(
        context: DefaultContext,
        universityId: String
    ) {
        val universityPrograms = universityProgramSource.getAllOfUniversityPrograms(universityId)
        context.call.respond(universityPrograms)
    }

    private suspend fun getUniversityProgram(
        context: DefaultContext,
        universityId: String,
        programId: String
    ) {
        val universityProgram = universityProgramSource.getUniversityProgramById(
            universityId,
            programId
        )
        if (universityProgram == null) {
            context.call.respond(HttpStatusCode.NotFound)
        } else {
            context.call.respond(universityProgram)
        }
    }
}
