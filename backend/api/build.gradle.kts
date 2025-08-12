plugins {
  java
  alias(libs.plugins.spotless)
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
  runtimeOnly(libs.spring.boot.devtools)

  implementation(libs.bundles.api)
  testImplementation(libs.bundles.test)

  implementation(project(":backend:core"))
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

openApi {
  apiDocsUrl.set("http://localhost:8080/spec/openapi.json")
  outputDir.set(rootProject.file("document/apispec"))
  outputFileName.set("openapi.json")
}

tasks.named("build") {
  dependsOn("generateOpenApiDocs")
}
