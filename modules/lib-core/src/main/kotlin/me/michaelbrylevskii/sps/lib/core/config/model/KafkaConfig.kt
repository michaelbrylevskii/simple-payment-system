package me.michaelbrylevskii.sps.lib.core.config.model

// TODO: move to kafka module
data class KafkaConfig(
    val bootstrapServers: List<String>,
)
