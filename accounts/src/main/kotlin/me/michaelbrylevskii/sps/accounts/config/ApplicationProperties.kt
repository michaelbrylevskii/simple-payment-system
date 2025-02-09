package me.michaelbrylevskii.sps.accounts.config

import me.michaelbrylevskii.sps.common.config.CommonProperties
import me.michaelbrylevskii.sps.common.config.DatabaseProperties
import me.michaelbrylevskii.sps.common.config.ServerProperties

data class ApplicationProperties(
    val common: CommonProperties,
    val server: ServerProperties = ServerProperties(),
    val database: DatabaseProperties,
)
