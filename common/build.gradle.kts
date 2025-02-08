plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    `java-library`
}

group = "com.michaelbrylevskii.sps"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}

dependencies {
    // Testing
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.params)
    testImplementation(libs.kotlin.test.junit5)
}
