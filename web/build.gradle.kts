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
    resources {
        srcDirs("../resources")
    }
}

application.mainClass.set("ru.disenchanted.web.RunWebAppKt")

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
                    environment()["outputDir"] = sourceSets.main.get().output.resourcesDir?.path
                }
                .start()
                .waitFor()
        }
    }
}
