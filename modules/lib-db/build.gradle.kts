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
    // Project
    implementation(project(":modules:lib-core"))
    // Database
    api(libs.hikari)
    api(libs.flyway.core)
    api(libs.flyway.database.postgresql)
    api(libs.postgresql)
    api(libs.exposed.core)
    api(libs.exposed.jdbc)
    // Testing
    testImplementation(testFixtures(project(":modules:lib-core")))
}
