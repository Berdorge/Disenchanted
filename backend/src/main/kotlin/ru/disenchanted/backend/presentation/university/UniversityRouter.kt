package ru.disenchanted.backend.presentation.university

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import ru.disenchanted.backend.domain.core.DefaultContext
import ru.disenchanted.backend.domain.university.UniversitySource
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Inject

private const val UNIVERSITY_ID = "id"

class UniversityRouter @Inject constructor(
    private val application: Application,
    private val universitySource: UniversitySource
) : ApplicationDelegate {
    override fun onApplicationReady() {
        application.routing {
            get("universities") {
                getUniversities(this)
            }
            get("universities/{$UNIVERSITY_ID}") {
                val universityId = call.parameters[UNIVERSITY_ID]
                if (universityId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    getUniversityById(this, universityId)
                }
            }
        }
    }

    private suspend fun getUniversities(context: DefaultContext) {
        val universities = universitySource.getUniversities()
        context.call.respond(universities)
    }

    private suspend fun getUniversityById(context: DefaultContext, universityId: String) {
        val university = universitySource.getUniversityById(universityId)
        if (university == null) {
            context.call.respond(HttpStatusCode.NotFound)
        } else {
            context.call.respond(university)
        }
    }
}
