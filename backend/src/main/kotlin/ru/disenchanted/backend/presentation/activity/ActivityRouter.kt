package ru.disenchanted.backend.presentation.activity

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import ru.disenchanted.backend.domain.activity.ActivitySource
import ru.disenchanted.backend.domain.core.DefaultContext
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Inject

private const val ACTIVITY_ID = "id"

class ActivityRouter @Inject constructor(
    private val application: Application,
    private val activitySource: ActivitySource
) : ApplicationDelegate {
    override fun onApplicationReady() {
        application.routing {
            get("activities") {
                getRootActivities(this)
            }
            get("activities/{$ACTIVITY_ID}") {
                val activityId = call.parameters[ACTIVITY_ID]
                if (activityId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    getActivity(this, activityId)
                }
            }
            get("activities/{$ACTIVITY_ID}/children") {
                val activityId = call.parameters[ACTIVITY_ID]
                if (activityId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    getChildActvities(this, activityId)
                }
            }
        }
    }

    private suspend fun getRootActivities(context: DefaultContext) {
        val activities = activitySource.getRootActivities()
        context.call.respond(activities)
    }

    private suspend fun getActivity(context: DefaultContext, activityId: String) {
        val activity = activitySource.getActivityById(activityId)
        if (activity == null) {
            context.call.respond(HttpStatusCode.NotFound)
        } else {
            context.call.respond(activity)
        }
    }

    private suspend fun getChildActvities(context: DefaultContext, parentId: String) {
        val childActivities = activitySource.getChildActivities(parentId)
        context.call.respond(childActivities)
    }
}
