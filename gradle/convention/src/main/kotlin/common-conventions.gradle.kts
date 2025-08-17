plugins {
    java
    id("com.diffplug.spotless")
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

group = "dev.ymkz.demo"
version = "1.0.0"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

spotless {
    java {
        palantirJavaFormat() // https://mvnrepository.com/artifact/com.palantir.javaformat/palantir-java-format
    }
    kotlinGradle {
        ktlint() // https://mvnrepository.com/artifact/com.pinterest.ktlint/ktlint-ruleset-standard
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
