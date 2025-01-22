plugins {
  java
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
  alias(libs.plugins.springdoc.openapi)
}

group = "dev.ymkz"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

dependencies {
  compileOnly(libs.lombok)
  annotationProcessor(libs.lombok)

  implementation(libs.spring.boot.starter.web)
  implementation(libs.spring.boot.starter.validation)
  implementation(libs.spring.boot.starter.actuator)
  implementation(libs.spring.boot.configuration.processor)
  implementation(libs.mybatis.spring.boot.starter)
  implementation(libs.micrometer.registry.prometheus)
  implementation(libs.springdoc.openapi.starter.webmvc.ui)

  testImplementation(libs.spring.boot.starter.test)
  testImplementation(libs.mockito.junit.jupiter)
  testImplementation(libs.rest.assured)

  implementation(project(":apps:core"))
}

tasks.compileJava {
  options.encoding = "UTF-8"
}

tasks.compileTestJava {
  options.encoding = "UTF-8"
}

tasks.withType<Test> {
  useJUnitPlatform()
}
