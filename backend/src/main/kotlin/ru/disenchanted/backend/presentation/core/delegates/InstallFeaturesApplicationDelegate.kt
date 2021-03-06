package ru.disenchanted.backend.presentation.core.delegates

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.Compression
import io.ktor.features.ContentNegotiation
import io.ktor.features.gzip
import io.ktor.serialization.json
import kotlinx.serialization.json.Json
import org.litote.kmongo.id.serialization.IdKotlinXSerializationModule
import javax.inject.Inject

class InstallFeaturesApplicationDelegate @Inject constructor(
    private val application: Application
) : ApplicationDelegate {
    override fun onApplicationReady() {
        with(application) {
            install(ContentNegotiation) {
                val json = Json {
                    serializersModule = IdKotlinXSerializationModule
                }
                json(json)
            }
            install(Compression) {
                gzip()
            }
            install(CallLogging)
        }
    }
}
