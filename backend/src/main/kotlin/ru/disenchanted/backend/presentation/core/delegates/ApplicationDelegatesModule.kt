package ru.disenchanted.backend.presentation.core.delegates

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.disenchanted.backend.presentation.core.Initializer
import javax.inject.Singleton

@Module
interface ApplicationDelegatesModule {
    @Binds
    @Singleton
    @Initializer
    @IntoSet
    fun bindInstallFeaturesApplicationDelegate(
        installFeaturesApplicationDelegate: InstallFeaturesApplicationDelegate
    ): ApplicationDelegate
}
