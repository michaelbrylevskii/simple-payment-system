package me.michaelbrylevskii.sps.common.config

data class ServerProperties(
    val port: Int = 80,
    val host: String = "0.0.0.0",
)
