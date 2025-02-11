package me.michaelbrylevskii.sps.app.accounts.config

import me.michaelbrylevskii.sps.lib.core.config.CommonProperties
import me.michaelbrylevskii.sps.lib.core.config.ServerProperties
import me.michaelbrylevskii.sps.lib.jdbc.config.DatabaseProperties

data class ApplicationProperties(
    val common: CommonProperties,
    val server: ServerProperties = ServerProperties(),
    val database: DatabaseProperties,
)
