package ru.disenchanted.backend.presentation.core

import dagger.BindsInstance
import dagger.Component
import io.ktor.application.Application
import ru.disenchanted.backend.data.DataModule
import ru.disenchanted.backend.domain.DomainModule
import ru.disenchanted.backend.presentation.PresentationModule
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class])
interface ApplicationComponent {
    val delegates: Set<@JvmSuppressWildcards ApplicationDelegate>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
