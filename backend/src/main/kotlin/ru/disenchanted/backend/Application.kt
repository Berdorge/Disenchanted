package ru.disenchanted.backend

import io.ktor.application.Application
import io.ktor.server.netty.EngineMain
import ru.disenchanted.backend.presentation.core.DaggerApplicationComponent

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.mainModule() {
    val component = DaggerApplicationComponent.factory()
        .create(this)
    for (delegate in component.delegates) {
        delegate.onApplicationReady()
    }
}
