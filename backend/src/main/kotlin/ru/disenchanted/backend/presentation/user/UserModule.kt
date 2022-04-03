package ru.disenchanted.backend.presentation.user

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.disenchanted.backend.presentation.core.Router
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Singleton

@Module
interface UserModule {
    @Binds
    @Router
    @IntoSet
    @Singleton
    fun bindUserRouter(userRouter: UserRouter): ApplicationDelegate
}
