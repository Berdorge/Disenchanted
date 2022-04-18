package ru.disenchanted.backend.presentation.activity

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import ru.disenchanted.backend.domain.activity.UniversityProgramActivitySource
import ru.disenchanted.backend.domain.core.DefaultContext
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Inject

private const val ACTIVITY_ID = "activity_id"
private const val UNIVERSITY_ID = "university_id"
private const val PROGRAM_ID = "program_id"

class UniversityProgramActivityRouter @Inject constructor(
    private val universityProgramActivitySource: UniversityProgramActivitySource,
    private val application: Application
) : ApplicationDelegate {
    override fun onApplicationReady() {
        application.routing {
            get("activities/{$ACTIVITY_ID}/universities") {
                val activityId = call.parameters[ACTIVITY_ID]
                if (activityId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    getActivityUniversities(this, activityId)
                }
            }
            get("universities/{$UNIVERSITY_ID}/activities") {
                val universityId = call.parameters[UNIVERSITY_ID]
                if (universityId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    getUniversityActivities(this, universityId)
                }
            }
            get("universities/{$UNIVERSITY_ID}/programs/{$PROGRAM_ID}/activities") {
                val universityId = call.parameters[UNIVERSITY_ID]
                val programId = call.parameters[PROGRAM_ID]
                if (universityId == null || programId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    getUniversityProgramActivities(this, universityId, programId)
                }
            }
        }
    }

    private suspend fun getActivityUniversities(
        context: DefaultContext,
        activityId: String
    ) {
        val universities = universityProgramActivitySource.getActivityUniversities(activityId)
        context.call.respond(universities)
    }

    private suspend fun getUniversityActivities(
        context: DefaultContext,
        universityId: String
    ) {
        val activities = universityProgramActivitySource.getUniversityActivities(universityId)
        context.call.respond(activities)
    }

    private suspend fun getUniversityProgramActivities(
        context: DefaultContext,
        universityId: String,
        programId: String
    ) {
        val activities = universityProgramActivitySource.getUniversityProgramActivities(
            universityId,
            programId
        )
        context.call.respond(activities)
    }
}
