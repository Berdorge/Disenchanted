package ru.disenchanted.backend.data.auth

import dagger.Binds
import dagger.Module
import ru.disenchanted.backend.domain.auth.JwtDataSource
import ru.disenchanted.backend.domain.auth.JwtGenerator
import ru.disenchanted.backend.domain.auth.PasswordGenerator
import ru.disenchanted.backend.domain.auth.PasswordValidator
import ru.disenchanted.backend.presentation.auth.JwtValidator
import javax.inject.Singleton

@Module
interface AuthDataModule {
    @Binds
    @Singleton
    fun bindJwtGenerator(jwtManager: JwtManager): JwtGenerator

    @Binds
    @Singleton
    fun bindJwtValidator(jwtManager: JwtManager): JwtValidator

    @Binds
    @Singleton
    fun bindJwtDataSource(jwtDataSourceImpl: JwtDataSourceImpl): JwtDataSource

    @Binds
    @Singleton
    fun bindPasswordGenerator(passwordManager: PasswordManager): PasswordGenerator

    @Binds
    @Singleton
    fun bindPasswordValidator(passwordManager: PasswordManager): PasswordValidator
}
