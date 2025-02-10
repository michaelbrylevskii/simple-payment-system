plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
    `java-library`
    `java-test-fixtures`
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
    // DI
    api(libs.koin.ktor)
    api(libs.koin.logger.slf4j)
    // Serialization
    api(libs.ktor.server.content.negotiation)
    api(libs.ktor.serialization.kotlinx.json)
    // Logging
    api(libs.kotlin.logging)
    api(libs.logback.classic)
    // Testing
    testFixturesApi(platform(libs.junit.bom))
    testFixturesApi(libs.junit.params)
    testFixturesApi(libs.kotlin.test.junit5)
    testFixturesApi(libs.ktor.server.test.host)
}
