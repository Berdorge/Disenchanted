package ru.disenchanted.backend.data.core.database

import dagger.Module
import dagger.Provides
import io.ktor.application.Application
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.id.serialization.IdKotlinXSerializationModule
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.serialization.registerModule
import javax.inject.Singleton

private const val CONNECTION = "database.connection"
private const val DATABASE_NAME = "database.name"

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): CoroutineDatabase {
        registerModule(IdKotlinXSerializationModule)
        val (connection, database) = with(application.environment.config) {
            property(CONNECTION).getString() to
                property(DATABASE_NAME).getString()
        }
        return KMongo.createClient(connection)
            .coroutine
            .getDatabase(database)
    }
}
