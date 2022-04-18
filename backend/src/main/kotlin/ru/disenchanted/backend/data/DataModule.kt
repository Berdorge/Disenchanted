package ru.disenchanted.backend.data

import dagger.Module
import ru.disenchanted.backend.data.activity.ActivityDataModule
import ru.disenchanted.backend.data.activity.UniversityProgramActivityModule
import ru.disenchanted.backend.data.auth.AuthDataModule
import ru.disenchanted.backend.data.core.database.DatabaseModule
import ru.disenchanted.backend.data.program.ProgramDataModule
import ru.disenchanted.backend.data.university.UniversityDataModule
import ru.disenchanted.backend.data.university.UniversityProgramDataModule
import ru.disenchanted.backend.data.user.UserDataModule

@Module(
    includes = [
        UserDataModule::class,
        AuthDataModule::class,
        DatabaseModule::class,
        UniversityDataModule::class,
        UniversityProgramDataModule::class,
        ProgramDataModule::class,
        ActivityDataModule::class,
        UniversityProgramActivityModule::class
    ]
)
interface DataModule
