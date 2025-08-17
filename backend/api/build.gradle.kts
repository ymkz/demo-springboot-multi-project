plugins {
    id("common-conventions")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.springdoc.openapi)
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

openApi {
    apiDocsUrl.set("http://localhost:8080/spec/openapi.json")
    outputDir.set(rootProject.file("document/apispec"))
    outputFileName.set("openapi.json")
}

tasks.named("build") {
    dependsOn("generateOpenApiDocs")
}
