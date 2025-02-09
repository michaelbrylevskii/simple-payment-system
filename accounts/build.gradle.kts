plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "me.michaelbrylevskii.sps"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("me.michaelbrylevskii.sps.accounts.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://packages.confluent.io/maven/") }
}

dependencies {
    // Project
    implementation(project(":common"))
    // Ktor core
//    implementation(libs.ktor.server.core)
//    implementation(libs.ktor.server.netty)
    //implementation(libs.ktor.server.config.yaml)
    // Serialization
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    // Database
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.postgresql)
    implementation(libs.h2) // TODO: remove it
    // Messaging
    implementation(libs.ktor.server.kafka)
    // Not sorted
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.swagger)
    implementation(libs.ktor.server.metrics)
    implementation(libs.ktor.server.metrics.micrometer)
    implementation(libs.micrometer.registry.prometheus)
    implementation(libs.logback.classic)
    // Testing
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.params)
    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.ktor.server.test.host)
}
