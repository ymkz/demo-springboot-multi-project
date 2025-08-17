plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

val spotlessVersion = "7.2.1"

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
}
