package ru.disenchanted.backend.presentation.core.delegates

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.Compression
import io.ktor.features.ContentNegotiation
import io.ktor.features.gzip
import io.ktor.http.HttpMethod
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
            install(CORS) {
                method(HttpMethod.Get)
                method(HttpMethod.Post)
                anyHost()
            }
            install(Compression) {
                gzip()
            }
            install(CallLogging)
        }
    }
}
