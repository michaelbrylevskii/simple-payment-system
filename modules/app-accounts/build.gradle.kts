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
    mainClass.set("me.michaelbrylevskii.sps.app.accounts.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://packages.confluent.io/maven/") }
}

dependencies {
    // Project
    implementation(project(":modules:lib-core"))
    implementation(project(":modules:lib-db"))
    // Messaging
    implementation(libs.ktor.server.kafka)
    // Not sorted
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.swagger)
    implementation(libs.ktor.server.metrics)
    implementation(libs.ktor.server.metrics.micrometer)
    implementation(libs.micrometer.registry.prometheus)
    // Testing
    testImplementation(testFixtures(project(":modules:lib-core")))
}
