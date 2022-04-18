package ru.disenchanted.backend.data.user

import dagger.Binds
import dagger.Module
import dagger.Provides
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import ru.disenchanted.backend.domain.user.User
import ru.disenchanted.backend.domain.user.UserSource
import javax.inject.Singleton

@Module(includes = [UserDataModule.DatabaseModule::class])
interface UserDataModule {
    @Binds
    @Singleton
    fun bindUserSource(databaseUserSource: DatabaseUserSource): UserSource

    @Module
    class DatabaseModule {
        @Provides
        @Singleton
        fun provideUserCollection(
            database: CoroutineDatabase
        ): CoroutineCollection<User> = database.getCollection()
    }
}
