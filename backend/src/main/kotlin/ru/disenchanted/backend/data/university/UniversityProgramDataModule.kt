package ru.disenchanted.backend.data.university

import dagger.Binds
import dagger.Module
import dagger.Provides
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import ru.disenchanted.backend.domain.university.UniversityProgramSource
import javax.inject.Singleton

@Module(includes = [UniversityProgramDataModule.DatabaseModule::class])
interface UniversityProgramDataModule {
    @Binds
    @Singleton
    fun bindUniversityProgramSource(
        databaseUniversityProgramSource: DatabaseUniversityProgramSource
    ): UniversityProgramSource

    @Module
    class DatabaseModule {
        @Provides
        @Singleton
        fun provideUniversityProgramCollection(
            database: CoroutineDatabase
        ): CoroutineCollection<UniversityProgramEntity> = database.getCollection()
    }
}
