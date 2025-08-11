plugins {
  alias(libs.plugins.spotless) apply false
}

group = "dev.ymkz"
version = "1.0.0"

subprojects {
  apply(plugin = "java")
  apply(plugin = "com.diffplug.spotless")
  
  repositories {
    mavenCentral()
  }
  
  configure<JavaPluginExtension> {
    toolchain {
      languageVersion.set(JavaLanguageVersion.of(21))
    }
  }

  tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }

  configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    java {
      palantirJavaFormat(libs.versions.palantir.java.format.get())
    }
  }
}