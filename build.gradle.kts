group = "me.shosha"
version = "1.0"

plugins {
    application
}

allprojects {
    repositories {
        mavenCentral()
    }
}

application.mainClass.set("Disenchanted")

dependencies {
    implementation(project(":backend"))
    implementation(project(":web"))
}
