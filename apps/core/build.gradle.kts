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
  implementation(libs.bundles.core)
  testImplementation(libs.bundles.test)
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

tasks.register<Exec>("prettierCheck") {
  commandLine("pnpx", "prettier", "--check", "src/**/*.java")
}

tasks.register<Exec>("prettierWrite") {
  commandLine("pnpx", "prettier", "--write", "src/**/*.java")
}

tasks.named("check") {
  dependsOn("prettierCheck")
}
