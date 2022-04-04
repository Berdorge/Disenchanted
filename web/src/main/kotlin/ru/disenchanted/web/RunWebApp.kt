package ru.disenchanted.web

import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths

private const val TEMP_DIR_PREFIX = "Disenchanted"
private val NPM_NAME = if (System.getProperty("os.name").contains("win", ignoreCase = true)) {
    "npm.cmd"
} else {
    "npm"
}

fun main() {
    val module = getModuleFile()
    val buildProcess = ProcessBuilder(NPM_NAME, "install", "--only=production", "--ignore-scripts")
        .inheritIO()
        .directory(module)
        .start()
    Runtime.getRuntime().addShutdownHook(Thread(buildProcess::destroy))
    check(buildProcess.waitFor() == 0) {
        "Web build failed"
    }
    val runProcess = ProcessBuilder("node", module.path).inheritIO().start()
    Runtime.getRuntime().addShutdownHook(Thread(runProcess::destroy))
    runProcess.waitFor()
}

private fun getModuleFile(): File {
    val jarPath = Paths.get(::main::class.java.protectionDomain.codeSource.location.toURI())
    return FileSystems.newFileSystem(jarPath, null as ClassLoader?).use { fileSystem ->
        val source = fileSystem.getPath("/")
        val destination = Files.createTempDirectory(TEMP_DIR_PREFIX)
        Files.walkFileTree(source, CopyDirectory(source, destination))
        destination.toFile()
    }
}
