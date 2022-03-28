package ru.disenchanted.backend.presentation.auth

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import ru.disenchanted.backend.presentation.core.delegates.ApplicationDelegate
import javax.inject.Singleton

@Module
interface AuthModule {
    @Binds
    @IntoSet
    @Singleton
    fun bindJwtApplicationDelegate(
        jwtApplicationDelegate: JwtApplicationDelegate
    ): ApplicationDelegate

    @Binds
    @IntoSet
    @Singleton
    fun bindAuthRouter(
        authRouter: AuthRouter
    ): ApplicationDelegate
}
