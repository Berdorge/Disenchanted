import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.BasicFileAttributes

class CopyDirectory(
    private val source: Path,
    private val destination: Path
) : SimpleFileVisitor<Path>() {
    override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
        val relativePath = source.relativize(dir).toString()
        val target = destination.resolve(relativePath)
        Files.createDirectories(target)
        return FileVisitResult.CONTINUE
    }

    override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
        val relativePath = source.relativize(file).toString()
        Files.copy(file, destination.resolve(relativePath), StandardCopyOption.REPLACE_EXISTING)
        return FileVisitResult.CONTINUE
    }
}
