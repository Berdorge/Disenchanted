package modules

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.Compression
import io.ktor.features.ContentNegotiation
import io.ktor.features.gzip
import io.ktor.http.HttpMethod
import io.ktor.serialization.json

fun Application.installFeatures() {
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        method(HttpMethod.Get)
        anyHost()
    }
    install(Compression) {
        gzip()
    }
}
