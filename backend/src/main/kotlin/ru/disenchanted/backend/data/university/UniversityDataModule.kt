package ru.disenchanted.backend.data.university

import dagger.Binds
import dagger.Module
import dagger.Provides
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import ru.disenchanted.backend.domain.university.University
import ru.disenchanted.backend.domain.university.UniversitySource
import javax.inject.Singleton

@Module(includes = [UniversityDataModule.DatabaseModule::class])
interface UniversityDataModule {
    @Binds
    @Singleton
    fun bindUniversitySource(databaseUniversitySource: DatabaseUniversitySource): UniversitySource

    @Module
    class DatabaseModule {
        @Provides
        @Singleton
        fun provideUniversityCollection(
            database: CoroutineDatabase
        ): CoroutineCollection<University> = database.getCollection()
    }
}
