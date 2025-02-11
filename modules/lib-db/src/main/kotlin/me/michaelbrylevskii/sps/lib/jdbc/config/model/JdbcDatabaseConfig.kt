package me.michaelbrylevskii.sps.lib.jdbc.config.model

import com.sksamuel.hoplite.Masked

data class JdbcDatabaseConfig(
    val url: String? = null,
    val username: String? = null,
    val password: Masked? = null,
    val schema: String? = null,
    val driverClassName: String? = null,
)
