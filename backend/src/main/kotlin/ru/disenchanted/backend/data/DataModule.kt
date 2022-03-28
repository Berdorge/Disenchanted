package ru.disenchanted.backend.data

import dagger.Module
import ru.disenchanted.backend.data.auth.AuthDataModule
import ru.disenchanted.backend.data.core.database.DatabaseModule
import ru.disenchanted.backend.data.user.UserDataModule

@Module(
    includes = [UserDataModule::class, AuthDataModule::class, DatabaseModule::class]
)
interface DataModule
