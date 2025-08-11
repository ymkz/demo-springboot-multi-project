plugins {
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.springdoc.openapi)
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

openApi {
  apiDocsUrl.set("http://localhost:8080/spec/openapi.json")
  outputDir.set(rootProject.file("docs/apispec"))
  outputFileName.set("openapi.json")
}

tasks.named("build") {
  dependsOn("generateOpenApiDocs")
}
