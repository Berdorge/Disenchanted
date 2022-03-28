package ru.disenchanted.backend.domain

import dagger.Module
import ru.disenchanted.backend.domain.core.CoreDomainModule

@Module(includes = [CoreDomainModule::class])
interface DomainModule
