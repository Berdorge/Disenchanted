plugins {
    kotlin("jvm") version "1.6.10"
    application
}

sourceSets.main {
    resources {
        srcDirs("../resources")
    }
}

application.mainClass.set("RunWebAppKt")

tasks {
    named("jar") {
        dependsOn("buildJs")
    }
    create("buildJs") {
        val npmName = if (System.getProperty("os.name").contains("win", ignoreCase = true)) {
            "npm.cmd"
        } else {
            "npm"
        }
        val outputDirectory = File(sourceSets.main.get().output.resourcesDir?.path, "web")
        doLast {
            ProcessBuilder(
                npmName,
                "run",
                "build",
                "--prefix",
                projectDir.path
            )
                .redirectErrorStream(true)
                .redirectOutput(File(buildDir.apply { mkdir() }, "buildJsOutput.txt"))
                .apply {
                    environment()["outputDir"] = outputDirectory.path
                }
                .start()
                .waitFor()
        }
    }
}
