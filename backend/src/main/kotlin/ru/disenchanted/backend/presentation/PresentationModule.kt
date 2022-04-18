package ru.disenchanted.backend.presentation

import dagger.Module
import ru.disenchanted.backend.presentation.activity.ActivityModule
import ru.disenchanted.backend.presentation.activity.UniversityProgramActivityModule
import ru.disenchanted.backend.presentation.auth.AuthModule
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegatesModule
import ru.disenchanted.backend.presentation.university.UniversityModule
import ru.disenchanted.backend.presentation.university.UniversityProgramModule
import ru.disenchanted.backend.presentation.user.UserModule

@Module(
    includes = [
        ApplicationDelegatesModule::class,
        AuthModule::class,
        UserModule::class,
        UniversityModule::class,
        UniversityProgramModule::class,
        ActivityModule::class,
        UniversityProgramActivityModule::class
    ]
)
interface PresentationModule
