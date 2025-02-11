package me.michaelbrylevskii.sps.lib.jdbc.config

import com.sksamuel.hoplite.Masked

data class DatabaseProperties(
    val url: String? = null,
    val username: String? = null,
    val password: Masked? = null,
    val schema: String? = null,
    val driverClassName: String? = null,
)
