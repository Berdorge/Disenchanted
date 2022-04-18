package ru.disenchanted.backend.data.activity

import dagger.Binds
import dagger.Module
import dagger.Provides
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import ru.disenchanted.backend.domain.activity.Activity
import ru.disenchanted.backend.domain.activity.ActivitySource
import javax.inject.Singleton

@Module(includes = [ActivityDataModule.DatabaseModule::class])
interface ActivityDataModule {
    @Binds
    @Singleton
    fun bindActivitySource(databaseActivitySource: DatabaseActivitySource): ActivitySource

    @Module
    class DatabaseModule {
        @Provides
        @Singleton
        fun provideActivityCollection(
            database: CoroutineDatabase
        ): CoroutineCollection<Activity> = database.getCollection()
    }
}
