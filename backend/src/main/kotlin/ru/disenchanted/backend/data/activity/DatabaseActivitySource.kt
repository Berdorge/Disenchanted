package ru.disenchanted.backend.data.activity

import kotlinx.coroutines.withContext
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq
import org.litote.kmongo.`in`
import ru.disenchanted.backend.domain.activity.Activity
import ru.disenchanted.backend.domain.activity.ActivitySource
import ru.disenchanted.backend.domain.activity.ChildActivity
import ru.disenchanted.backend.domain.core.DispatchersProvider
import javax.inject.Inject

class DatabaseActivitySource @Inject constructor(
    private val collection: CoroutineCollection<Activity>,
    private val dispatchersProvider: DispatchersProvider
) : ActivitySource {
    override suspend fun getRootActivities(): List<Activity> = withContext(dispatchersProvider.io) {
        collection.find(Activity::parentId eq null)
            .toList()
    }

    override suspend fun getChildActivities(
        parentId: String
    ): List<Activity> = withContext(dispatchersProvider.io) {
        collection.find(Activity::parentId eq parentId)
            .toList()
    }

    override suspend fun getActivityById(
        id: String
    ): ChildActivity? = withContext(dispatchersProvider.io) {
        val activity = collection.findOne(Activity::id eq id)
        val parent = activity?.parentId?.let { parentId ->
            collection.findOne(Activity::id eq parentId)
        }
        activity?.withParent(parent)
    }

    override suspend fun getActivitiesByIds(
        ids: List<String>
    ): List<Activity> = withContext(dispatchersProvider.io) {
        collection.find(Activity::id `in` ids)
            .toList()
    }
}
