package ru.disenchanted.backend.presentation.core.delegates

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
interface ApplicationDelegatesModule {
    @Binds
    @IntoSet
    @Singleton
    fun bindInstallFeaturesApplicationDelegate(
        installFeaturesApplicationDelegate: InstallFeaturesApplicationDelegate
    ): ApplicationDelegate
}
