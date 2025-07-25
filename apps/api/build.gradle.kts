plugins {
  java
  alias(libs.plugins.spring.boot)
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
  implementation(platform(libs.springboot.bom))
  implementation(platform(libs.junit.bom))

  compileOnly(libs.lombok)
  annotationProcessor(libs.lombok)
  runtimeOnly(libs.spring.boot.devtools)
  implementation(libs.bundles.api)
  testImplementation(libs.bundles.test)

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
  commandLine("pnpm", "prettier", "--check", "**/src/**/*.java")
}

tasks.register<Exec>("prettierWrite") {
  commandLine("pnpm", "prettier", "--write", "**/src/**/*.java")
}

tasks.named("check") {
  dependsOn("prettierCheck")
}

tasks.named("build") {
  dependsOn("generateOpenApiDocs")
}
