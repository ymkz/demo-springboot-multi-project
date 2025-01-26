plugins {
  java
  alias(libs.plugins.spring.boot) apply false
  alias(libs.plugins.spring.dependency.management)
}

group = "dev.ymkz"
version = "1.0.0"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
  }
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

dependencyManagement {
  imports {
    mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
  }
}

dependencies {
  compileOnly(libs.lombok)
  annotationProcessor(libs.lombok)

  implementation(libs.spring.boot.starter.web)
  implementation(libs.spring.boot.starter.validation)
  implementation(libs.spring.boot.configuration.processor)
  implementation(libs.mybatis.spring.boot.starter)
  implementation(libs.mysql.connector.j)

  testImplementation(libs.spring.boot.starter.test)
  testImplementation(libs.mockito.junit.jupiter)
  testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.compileJava {
  options.encoding = "UTF-8"
  options.compilerArgs.add("-parameters")
}

tasks.compileTestJava {
  options.encoding = "UTF-8"
}

tasks.withType<Test> {
  useJUnitPlatform()
}
