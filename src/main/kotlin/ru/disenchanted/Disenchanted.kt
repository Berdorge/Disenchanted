package ru.disenchanted

import kotlin.concurrent.thread
import ru.disenchanted.backend.main as backendMain
import ru.disenchanted.web.main as webMain

fun main(args: Array<String>) {
    thread(start = true, block = ::webMain)
    backendMain(args)
}
