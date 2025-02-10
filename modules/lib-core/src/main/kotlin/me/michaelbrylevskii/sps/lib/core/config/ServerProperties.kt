package me.michaelbrylevskii.sps.lib.core.config

data class ServerProperties(
    val port: Int = 80,
    val host: String = "0.0.0.0",
)
