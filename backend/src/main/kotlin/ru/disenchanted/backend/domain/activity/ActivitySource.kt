package ru.disenchanted.backend.domain.activity

interface ActivitySource {
    suspend fun getRootActivities(): List<Activity>
    suspend fun getChildActivities(parentId: String): List<Activity>
    suspend fun getActivityById(id: String): ChildActivity?
    suspend fun getActivitiesByIds(ids: List<String>): List<Activity>
}
