package ru.disenchanted.backend.presentation

import dagger.Module
import ru.disenchanted.backend.presentation.auth.AuthModule
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegatesModule

@Module(
    includes = [ApplicationDelegatesModule::class, AuthModule::class]
)
interface PresentationModule
