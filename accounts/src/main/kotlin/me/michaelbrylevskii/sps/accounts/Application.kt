package me.michaelbrylevskii.sps.accounts

import io.ktor.server.application.*
import me.michaelbrylevskii.sps.accounts.config.ApplicationProperties
import me.michaelbrylevskii.sps.accounts.example.configureFrameworks
import me.michaelbrylevskii.sps.accounts.example.configureRouting
import me.michaelbrylevskii.sps.common.util.ApplicationUtil

fun main(args: Array<String>) {
    val properties = ApplicationUtil.loadProperties<ApplicationProperties>()

    ApplicationUtil.startApplication(properties.server, Application::modules)
}

fun Application.modules() {
    configureFrameworks()
    configureRouting()
}
