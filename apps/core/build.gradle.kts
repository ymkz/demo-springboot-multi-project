plugins {
  java
  alias(libs.plugins.spring.boot) apply false
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
  commandLine("pnpm", "prettier", "--check", "**/src/**/*.java")
}

tasks.register<Exec>("prettierWrite") {
  commandLine("pnpm", "prettier", "--write", "**/src/**/*.java")
}

tasks.named("check") {
  dependsOn("prettierCheck")
}
