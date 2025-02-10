package me.michaelbrylevskii.sps.accounts

import io.ktor.server.application.*
import me.michaelbrylevskii.sps.accounts.config.ApplicationProperties
import me.michaelbrylevskii.sps.accounts.example.configureFrameworks
import me.michaelbrylevskii.sps.accounts.example.configureRouting
import me.michaelbrylevskii.sps.common.util.ApplicationUtil.loadProperties
import me.michaelbrylevskii.sps.common.util.ApplicationUtil.startApplication

object ApplicationStarter {
    @JvmStatic
    fun main(args: Array<String>) {
        val properties = loadProperties<ApplicationProperties>()
        startApplication(properties.server) { loadModules(properties) }
    }

    fun Application.loadModules(properties: ApplicationProperties) {
        configureFrameworks()
        configureRouting()
    }
}
