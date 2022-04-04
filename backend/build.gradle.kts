import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.6.10"
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
}

sourceSets.main {
    dependencies {
        val serializationVersion = "1.3.2"
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

        val ktorVersion = "1.6.7"
        implementation("io.ktor:ktor-serialization:$ktorVersion")
        implementation("io.ktor:ktor-server-core:$ktorVersion")
        implementation("io.ktor:ktor-server-netty:$ktorVersion")
        implementation("io.ktor:ktor-auth:$ktorVersion")
        implementation("io.ktor:ktor-auth-jwt:$ktorVersion")

        val daggerVersion = "2.41"
        implementation("com.google.dagger:dagger:$daggerVersion")
        kapt("com.google.dagger:dagger-compiler:$daggerVersion")

        val kmongoVersion = "4.5.0"
        implementation("org.litote.kmongo:kmongo-coroutine-serialization:$kmongoVersion")
        implementation("org.litote.kmongo:kmongo-id-serialization:$kmongoVersion")

        implementation("org.mindrot:jbcrypt:0.4")

        implementation("ch.qos.logback:logback-classic:1.2.11")
        implementation("org.codehaus.janino:janino:3.1.6")
    }
    resources {
        srcDirs("../resources")
    }
}

application.mainClass.set("ru.disenchanted.backend.ApplicationKt")

sourceSets.main {
    java {
        srcDirs("src/main/kotlin")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs.plus("-opt-in=kotlin.RequiresOptIn")
    }
}
