package me.michaelbrylevskii.sps.app.accounts

import io.ktor.server.application.*
import me.michaelbrylevskii.sps.app.accounts.config.ApplicationConfig
import me.michaelbrylevskii.sps.app.accounts.example.configureFrameworks
import me.michaelbrylevskii.sps.app.accounts.example.configureRouting
import me.michaelbrylevskii.sps.lib.core.ApplicationStarter.startApplication
import me.michaelbrylevskii.sps.lib.jdbc.config.JdbcDatabaseConfigurer.configureJdbcDatabase

object ApplicationStarter {
    @JvmStatic
    fun main(args: Array<String>) {
        startApplication<ApplicationConfig> { config ->
            loadModules(config)
        }
    }

    fun Application.loadModules(config: ApplicationConfig) {
        configureJdbcDatabase(config.database)
        configureFrameworks()
        configureRouting()
    }
}
