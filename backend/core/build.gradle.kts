plugins {
  alias(libs.plugins.spring.boot) apply false
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
