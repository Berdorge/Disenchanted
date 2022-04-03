package ru.disenchanted.backend.presentation.auth

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.disenchanted.backend.presentation.core.Initializer
import ru.disenchanted.backend.presentation.core.Router
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Singleton

@Module
interface AuthModule {
    @Binds
    @Singleton
    @Initializer
    @IntoSet
    fun bindJwtApplicationDelegate(
        jwtApplicationDelegate: JwtApplicationDelegate
    ): ApplicationDelegate

    @Binds
    @Singleton
    @Router
    @IntoSet
    fun bindAuthRouter(
        authRouter: AuthRouter
    ): ApplicationDelegate
}
