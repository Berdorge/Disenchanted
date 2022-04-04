import java.nio.file.Files
import java.nio.file.Path

plugins {
    kotlin("jvm")
    application
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
}

sourceSets.main {
    java {
        srcDirs("src/main/kotlin")
    }
}

application.mainClass.set("ru.disenchanted.web.RunWebAppKt")

tasks {
    named("jar") {
        dependsOn("buildJs")
    }
    create("buildJs") {
        val outputDir = checkNotNull(sourceSets.main.get().output.resourcesDir) {
            "Resource directory is not configured."
        }
        val npmName = if (System.getProperty("os.name").contains("win", ignoreCase = true)) {
            "npm.cmd"
        } else {
            "npm"
        }
        doFirst {
            val process = ProcessBuilder(npmName, "run", "build")
                .directory(projectDir)
                .apply {
                    environment()["outputDir"] = outputDir.path
                }
                .start()
            while (process.isAlive) {
                if (process.errorStream.available() > 0) {
                    System.err.write(process.errorStream.read())
                }
                if (process.inputStream.available() > 0) {
                    System.out.write(process.inputStream.read())
                }
            }
            check(process.waitFor() == 0) {
                "JS build failed. See the logs above."
            }
        }
        doLast {
            val source = Path.of(File(projectDir, "package.json").path)
            val destination = Path.of(File(outputDir, "package.json").path)
            Files.copy(source, destination)
        }
    }
}
