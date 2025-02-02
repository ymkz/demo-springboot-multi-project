plugins {
  java
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
  alias(libs.plugins.springdoc.openapi)
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

dependencies {
  compileOnly(libs.lombok)
  annotationProcessor(libs.lombok)

  developmentOnly(libs.spring.boot.devtools)

  implementation(libs.spring.boot.starter.web)
  implementation(libs.spring.boot.starter.validation)
  implementation(libs.spring.boot.starter.actuator)
  implementation(libs.spring.boot.configuration.processor)
  implementation(libs.mybatis.spring.boot.starter)
  implementation(libs.micrometer.registry.prometheus)
  implementation(libs.springdoc.openapi.starter.webmvc.ui)
  implementation(libs.jackson.dataformat.csv)

  testImplementation(libs.spring.boot.starter.test)
  testImplementation(libs.mockito.junit.jupiter)
  testImplementation(libs.rest.assured)
  testRuntimeOnly(libs.junit.platform.launcher)

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

openApi {
  apiDocsUrl.set("http://localhost:8080/spec/openapi.json")
  outputDir.set(rootProject.file("docs/apispec"))
  outputFileName.set("openapi.json")
}

tasks.register<Exec>("prettierCheck") {
  commandLine("pnpx", "prettier", "--check", "src/**/*.java")
}

tasks.register<Exec>("prettierWrite") {
  commandLine("pnpx", "prettier", "--write", "src/**/*.java")
}

tasks.named("check") {
  dependsOn("prettierCheck")
}

tasks.named("build") {
  dependsOn("generateOpenApiDocs")
}
