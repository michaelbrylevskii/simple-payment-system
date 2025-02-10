package me.michaelbrylevskii.sps.app.accounts

import io.ktor.server.application.*
import me.michaelbrylevskii.sps.app.accounts.config.ApplicationProperties
import me.michaelbrylevskii.sps.app.accounts.example.configureFrameworks
import me.michaelbrylevskii.sps.app.accounts.example.configureRouting
import me.michaelbrylevskii.sps.lib.core.util.ApplicationUtil.loadProperties
import me.michaelbrylevskii.sps.lib.core.util.ApplicationUtil.startApplication

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
