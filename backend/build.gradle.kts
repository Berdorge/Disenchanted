import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
    kotlin("plugin.serialization") version "1.6.10"
}

sourceSets.main {
    dependencies {
        val serializationVersion = "1.3.2"
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

        val ktorVersion = "1.6.7"
        implementation("io.ktor:ktor-serialization:$ktorVersion")
        implementation("io.ktor:ktor-server-core:$ktorVersion")
        implementation("io.ktor:ktor-server-netty:$ktorVersion")
    }
    resources {
        srcDirs("../resources")
    }
}

application {
    mainClass.set("ServerKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs.plus("-opt-in=kotlin.RequiresOptIn")
    }
}
