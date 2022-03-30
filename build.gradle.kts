group = "me.shosha"
version = "1.0"

plugins {
    application
    kotlin("jvm") version "1.6.10"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

application.mainClass.set("ru.disenchanted.DisenchantedKt")

sourceSets.main {
    java {
        srcDirs("src/main/kotlin")
    }
}

dependencies {
    implementation(project(":backend"))
    implementation(project(":web"))
}
