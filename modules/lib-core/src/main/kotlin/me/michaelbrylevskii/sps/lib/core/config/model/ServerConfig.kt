package me.michaelbrylevskii.sps.lib.core.config.model

data class ServerConfig(
    val port: Int = 80,
    val host: String = "0.0.0.0",
)
