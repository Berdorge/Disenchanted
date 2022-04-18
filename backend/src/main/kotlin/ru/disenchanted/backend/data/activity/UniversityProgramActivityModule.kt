package ru.disenchanted.backend.data.activity

import dagger.Binds
import dagger.Module
import dagger.Provides
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import ru.disenchanted.backend.domain.activity.UniversityProgramActivitySource
import javax.inject.Singleton

@Module(includes = [UniversityProgramActivityModule.DatabaseModule::class])
interface UniversityProgramActivityModule {
    @Binds
    @Singleton
    fun bindUniversityProgramActivitySource(
        databaseUniversityProgramActivitySource: DatabaseUniversityProgramActivitySource
    ): UniversityProgramActivitySource

    @Module
    class DatabaseModule {
        @Provides
        @Singleton
        fun provideUniversityProgramActivityCollection(
            database: CoroutineDatabase
        ): CoroutineCollection<UniversityProgramActivityEntity> = database.getCollection()
    }
}
