package ru.disenchanted.backend.presentation.activity

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.disenchanted.backend.presentation.core.Router
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Singleton

@Module
interface ActivityModule {
    @Binds
    @Singleton
    @Router
    @IntoSet
    fun bindActivityRouter(activityRouter: ActivityRouter): ApplicationDelegate
}
