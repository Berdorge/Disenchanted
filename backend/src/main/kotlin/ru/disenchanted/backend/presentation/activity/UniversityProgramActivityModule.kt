package ru.disenchanted.backend.presentation.activity

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.disenchanted.backend.presentation.core.Router
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Singleton

@Module
interface UniversityProgramActivityModule {
    @Binds
    @Singleton
    @Router
    @IntoSet
    fun bindUniversityProgramActivityRouter(
        universityProgramActivityRouter: UniversityProgramActivityRouter
    ): ApplicationDelegate
}
