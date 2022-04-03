package ru.disenchanted.backend.presentation

import dagger.Module
import ru.disenchanted.backend.presentation.auth.AuthModule
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegatesModule
import ru.disenchanted.backend.presentation.user.UserModule

@Module(
    includes = [ApplicationDelegatesModule::class, AuthModule::class, UserModule::class]
)
interface PresentationModule
