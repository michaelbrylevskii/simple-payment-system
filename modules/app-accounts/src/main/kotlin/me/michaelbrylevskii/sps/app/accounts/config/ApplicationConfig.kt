package me.michaelbrylevskii.sps.app.accounts.config

import me.michaelbrylevskii.sps.lib.core.config.model.CoreApplicationConfig
import me.michaelbrylevskii.sps.lib.core.config.model.CommonConfig
import me.michaelbrylevskii.sps.lib.core.config.model.ServerConfig
import me.michaelbrylevskii.sps.lib.jdbc.config.model.JdbcDatabaseConfig

data class ApplicationConfig(
    override val common: CommonConfig,
    override val server: ServerConfig = ServerConfig(),
    val database: JdbcDatabaseConfig,
) : CoreApplicationConfig
