package ru.disenchanted.backend.data.program

import dagger.Binds
import dagger.Module
import dagger.Provides
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import ru.disenchanted.backend.domain.program.Program
import ru.disenchanted.backend.domain.program.ProgramSource
import javax.inject.Singleton

@Module(includes = [ProgramDataModule.DatabaseModule::class])
interface ProgramDataModule {
    @Binds
    @Singleton
    fun bindProgramSource(databaseProgramSource: DatabaseProgramSource): ProgramSource

    @Module
    class DatabaseModule {
        @Provides
        @Singleton
        fun provideProgramCollection(
            database: CoroutineDatabase
        ): CoroutineCollection<Program> = database.getCollection()
    }
}
