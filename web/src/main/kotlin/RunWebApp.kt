import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths

private const val TEMP_DIR_PREFIX = "Disenchanted"

fun main() {
    val process = ProcessBuilder("node", getModuleFilePath()).inheritIO().start()
    Runtime.getRuntime().addShutdownHook(Thread(process::destroy))
    process.waitFor()
}

private fun getModuleFilePath(): String {
    val jarPath = Paths.get(::main::class.java.protectionDomain.codeSource.location.toURI())
    return FileSystems.newFileSystem(jarPath, null).use { fileSystem ->
        val source = fileSystem.getPath("/web")
        val destination = Files.createTempDirectory(TEMP_DIR_PREFIX)
        Files.walkFileTree(source, CopyDirectory(source, destination))
        destination.toString()
    }
}
