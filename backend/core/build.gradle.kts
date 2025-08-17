plugins {
    id("common-conventions")
    alias(libs.plugins.spring.boot) apply false
}

dependencies {
    implementation(platform(libs.springboot.bom))
    implementation(platform(libs.junit.bom))

    annotationProcessor(libs.lombok)
    compileOnly(libs.lombok)

    implementation(libs.bundles.base)

    testImplementation(libs.bundles.test)
}
