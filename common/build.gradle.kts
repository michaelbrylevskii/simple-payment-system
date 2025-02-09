plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    `java-library`
}

group = "me.michaelbrylevskii.sps"
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
    // Configuration
    api(libs.hoplite.core)
    api(libs.hoplite.yaml)
    // Ktor core
    api(libs.ktor.server.core)
    api(libs.ktor.server.netty)
    // Serialization
    //implementation(libs.kaml)
    // Logging
    api(libs.kotlin.logging)
    // Testing
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.params)
    testImplementation(libs.kotlin.test.junit5)
}
