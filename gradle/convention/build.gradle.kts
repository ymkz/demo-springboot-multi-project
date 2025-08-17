plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

val spotlessVersion = "7.2.1" // https://mvnrepository.com/artifact/com.diffplug.spotless/spotless-plugin-gradle

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
}
