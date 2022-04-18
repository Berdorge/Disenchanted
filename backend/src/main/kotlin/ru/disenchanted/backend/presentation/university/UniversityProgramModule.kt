package ru.disenchanted.backend.presentation.university

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.disenchanted.backend.presentation.core.Router
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Singleton

@Module
interface UniversityProgramModule {
    @Binds
    @Singleton
    @Router
    @IntoSet
    fun bindUniversityProgramRouter(
        universityProgramRouter: UniversityProgramRouter
    ): ApplicationDelegate
}
