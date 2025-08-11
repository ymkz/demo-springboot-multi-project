plugins {
  java
  alias(libs.plugins.spotless)
  alias(libs.plugins.spring.boot) apply false
}

group = "dev.ymkz"
version = "1.0.0"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
  }
}

repositories {
  mavenCentral()
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

dependencies {
  implementation(platform(libs.springboot.bom))
  implementation(platform(libs.junit.bom))

  annotationProcessor(libs.lombok)
  compileOnly(libs.lombok)

  implementation(libs.bundles.core)
  testImplementation(libs.bundles.test)
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

spotless {
  java {
    palantirJavaFormat(libs.versions.palantir.java.format.get())
  }
}
